package org.neophyte.popularmovie.popularmovie.network;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.neophyte.popularmovie.popularmovie.network.pojo.Review;
import org.neophyte.popularmovie.popularmovie.network.pojo.ReviewResult;
import org.neophyte.popularmovie.popularmovie.network.pojo.TrailerResult;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by juankarsten on 2/9/17.
 */

public class ReviewDatabase {
    private String mApiKey;
    private String mVideoId;
    private OnResult mOnResult;

    public ReviewDatabase(String apiKey, String videoId, OnResult onResult) {
        mApiKey = apiKey;
        mVideoId = videoId;
        mOnResult = onResult;
        URL url = buildReviewsURL(videoId);
        NetworkReviewDB networkReviewDB = new NetworkReviewDB();
        networkReviewDB.execute(url);
    }

    private URL buildReviewsURL(String videoId) {
        Uri builtUri = Uri.parse(MovieDatabaseEndpoint.BASE_URL).buildUpon()
                .appendEncodedPath(MovieDatabaseEndpoint.MOVIE)
                .appendEncodedPath(videoId)
                .appendEncodedPath(MovieDatabaseEndpoint.REVIEWS)
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


    private class NetworkReviewDB extends AsyncTask<URL, Void, ReviewResult> {
        @Override
        protected ReviewResult doInBackground(URL... params) {
            URL reviewsURL = params[0];
            String response = null;
            try {
                response = NetworkUtils.getResponseFromHttpUrl(reviewsURL);
                Gson gson = new GsonBuilder().create();
                ReviewResult result = gson.fromJson(response, ReviewResult.class);
                Log.d(ReviewDatabase.class.getName(), result.toString());
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(MovieDatabase.class.getName(), response);
            return null;
        }

        @Override
        protected void onPostExecute(ReviewResult reviewResult) {
            if (reviewResult != null) {
                mOnResult.onReviewResult(reviewResult.results);
            }
        }
    }

    public interface OnResult {
        void onReviewResult(List<Review> results);
    }
}
