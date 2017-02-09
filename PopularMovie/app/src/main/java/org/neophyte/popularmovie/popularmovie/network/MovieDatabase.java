package org.neophyte.popularmovie.popularmovie.network;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.neophyte.popularmovie.popularmovie.network.pojo.MovieResult;
import org.neophyte.popularmovie.popularmovie.network.pojo.Movies;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juankarsten on 2/8/17.
 */

public class MovieDatabase{
    public static final int TOP_RATED = 1;
    public static final int POPULARITY = 2;
    private int currentSortBy;

    private int mCurrentPage;

    private String mApiKey;
    List<Movies> mMovies;
    private OnResult onResult;

    public MovieDatabase(String apiKey) {
        mApiKey = apiKey;
        mCurrentPage = 1;
        mMovies = new ArrayList<>();
        currentSortBy = POPULARITY;
        reinit();
    }

    public void changeSortCriteria(int criteria) {
        if (currentSortBy != criteria) {
            currentSortBy = criteria;
            mMovies.clear();
            mCurrentPage = 1;
            reinit();
        }
    }

    private URL buildPopularMovieUrl() {
        String movieEndPoint = MovieDatabaseEndpoint.MOVIE_POPULAR_ENDPOINT;
        if (currentSortBy == TOP_RATED) {
            movieEndPoint = MovieDatabaseEndpoint.MOVIE_TOP_RATED_ENDPOINT;
        }
        Uri builtUri = Uri.parse(MovieDatabaseEndpoint.BASE_URL).buildUpon()
                .appendEncodedPath(movieEndPoint)
                .appendQueryParameter(MovieDatabaseEndpoint.PARAM_API, mApiKey)
                .appendQueryParameter(MovieDatabaseEndpoint.PARAM_PAGE, mCurrentPage+"")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public void nextPage() {
        mCurrentPage += 1;
    }

    public void resetDatabase() {
        mCurrentPage = 1;
        mMovies.clear();
    }


    public void setOnResult(OnResult onResult) {
        this.onResult = onResult;
    }

    public void reinit() {
        NetworkMovieDatabase networkMovieDatabase = new NetworkMovieDatabase();
        networkMovieDatabase.execute();
    }

    public static URL buildImageString(String image) {
        Uri builtUri = Uri.parse(MovieDatabaseEndpoint.BASE_IMAGE_URL  ).buildUpon()
                .appendPath(MovieDatabaseEndpoint.PATH_SIZE)
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

    public interface OnResult {
        void onResult(List<Movies> movies);
    }

    private class NetworkMovieDatabase extends AsyncTask<Void, Void, MovieResult> {
        @Override
        protected MovieResult doInBackground(Void... params) {
            URL url = buildPopularMovieUrl();

            MovieResult mResult = null;
            try {
                String response = NetworkUtils.getResponseFromHttpUrl(url);
                Log.d(MovieDatabase.class.getName(), response);

                Gson gson = new GsonBuilder().create();
                mResult = gson.fromJson(response, MovieResult.class);
                Log.d(MovieDatabase.class.getName(), mResult.toString());
                if (mResult != null) {
                    mMovies.addAll(mResult.results);
                    //nextPage();
                }
            } catch (IOException e) {
                Log.d(MovieDatabase.class.getName(), e.toString());
            }

            return mResult;
        }

        @Override
        protected void onPostExecute(MovieResult movieResult) {
            if (mMovies != null) {
                onResult.onResult(mMovies);
            }
        }
    }

}
