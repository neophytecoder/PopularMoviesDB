package org.neophyte.popularmovie.popularmovie.network;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by juankarsten on 2/8/17.
 */

public class MovieDatabase extends AsyncTask<String, Void, MovieResult>{

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String MOVIE_ENDPOINT = "movie/popular";
    private static final String PARAM_API = "api_key";

    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/";
    private static final String PATH_SIZE = "w500";

    private String mApiKey;
    MovieResult mResult;
    private OnResult onResult;

    public MovieDatabase(String apiKey) {
        this.mApiKey = apiKey;
    }

    private URL buildPopularMovieUrl() {
        Uri builtUri = Uri.parse(BASE_URL + MOVIE_ENDPOINT).buildUpon()
                .appendQueryParameter(PARAM_API, mApiKey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }



    public void setOnResult(OnResult onResult) {
        this.onResult = onResult;
    }

    @Override
    protected MovieResult doInBackground(String... params) {
        URL url = buildPopularMovieUrl();

        try {
            String response = NetworkUtils.getResponseFromHttpUrl(url);
            Log.d(MovieDatabase.class.getName(), response);

            Gson gson = new GsonBuilder().create();
            mResult = gson.fromJson(response, MovieResult.class);
            Log.d(MovieDatabase.class.getName(), mResult.toString());

        } catch (IOException e) {
            Log.d(MovieDatabase.class.getName(), e.toString());
        }

        return mResult;
    }

    @Override
    protected void onPostExecute(MovieResult movieResult) {
        if (mResult != null) {
            onResult.onResult(mResult.results);
        }
    }

    public static URL buildImageString(String image) {
        Uri builtUri = Uri.parse(BASE_IMAGE_URL  ).buildUpon()
                .appendPath(PATH_SIZE)
                .appendEncodedPath(image)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static interface OnResult {
        public void onResult(List<Movies> movies);
    }

}
