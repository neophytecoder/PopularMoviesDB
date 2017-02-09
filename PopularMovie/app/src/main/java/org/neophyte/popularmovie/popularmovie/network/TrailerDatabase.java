package org.neophyte.popularmovie.popularmovie.network;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.neophyte.popularmovie.popularmovie.network.pojo.MovieResult;
import org.neophyte.popularmovie.popularmovie.network.pojo.Trailer;
import org.neophyte.popularmovie.popularmovie.network.pojo.TrailerResult;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juankarsten on 2/9/17.
 */

public class TrailerDatabase {
    private String mApiKey;
    private OnResult onResult;
    private String mVideoId;

    public TrailerDatabase(String apiKey, String videoId, OnResult onResult) {
        mApiKey = apiKey;
        this.onResult = onResult;
        mVideoId = videoId;

        init();
    }

    private void init() {
        URL url = buildVideoURL(mVideoId);
        TrailerThread thread = new TrailerThread();
        thread.execute(url);
    }

    private URL buildYoutubeUrl(String key) {
        Uri builtUri = Uri.parse("https://www.youtube.com/watch").buildUpon()
                .appendQueryParameter("v", key)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    private void buildYoutubeURLs(List<Trailer> trailers) {
        for (Trailer trailer:trailers) {
            trailer.youtubeURL = buildYoutubeUrl(trailer.key);
        }
    }

    private URL buildVideoURL(String videoId) {
        Uri builtUri = Uri.parse(MovieDatabaseEndpoint.BASE_URL).buildUpon()
                .appendEncodedPath(MovieDatabaseEndpoint.MOVIE)
                .appendEncodedPath(videoId)
                .appendEncodedPath(MovieDatabaseEndpoint.VIDEOS)
                .appendQueryParameter("api_key", mApiKey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }




    private class TrailerThread extends AsyncTask<URL, Void, TrailerResult> {
        @Override
        protected TrailerResult doInBackground(URL... params) {
            TrailerResult mResult = null;
            try {
                URL url = params[0];
                String response = NetworkUtils.getResponseFromHttpUrl(url);
                Log.d(MovieDatabase.class.getName(), response);

                Gson gson = new GsonBuilder().create();
                mResult = gson.fromJson(response, TrailerResult.class);
                Log.d(MovieDatabase.class.getName(), mResult.toString());
            } catch (IOException e) {
                Log.d(MovieDatabase.class.getName(), e.toString());
            }
            return mResult;
        }

        @Override
        protected void onPostExecute(TrailerResult trailerResult) {
            if (trailerResult != null) {
                List<Trailer> trailers = trailerResult.results;
                buildYoutubeURLs(trailers);
                Log.d(TrailerDatabase.class.getName(), trailers.toString());
                onResult.onResult(trailers);
            }
        }
    }

    public interface OnResult {
        void onResult(List<Trailer> trailers);
    }
}
