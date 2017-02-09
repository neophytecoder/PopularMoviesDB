package org.neophyte.popularmovie.popularmovie.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.neophyte.popularmovie.popularmovie.R;
import org.neophyte.popularmovie.popularmovie.network.MovieDatabase;
import org.neophyte.popularmovie.popularmovie.network.pojo.Movies;

import java.net.URL;

/**
 * Created by juankarsten on 2/9/17.
 */

public class DetailHeaderViewHolder extends RecyclerView.ViewHolder{
    private ImageView mImagePoster;
    private TextView mTvYear;
    private TextView mTvDuration;
    private TextView mTvRating;
    private Button mBtnMark;
    private TextView mTvSynopsis;
    private TextView mTvTitle;

    public DetailHeaderViewHolder(View view, Movies movie, Context context) {
        super(view);

        mImagePoster = (ImageView) view.findViewById(R.id.iv_poster);
        mTvYear = (TextView) view.findViewById(R.id.tv_year);
        mTvDuration = (TextView) view.findViewById(R.id.tv_duration);
        mTvRating = (TextView) view.findViewById(R.id.tv_rating);
        mBtnMark = (Button) view.findViewById(R.id.btn_mark);
        mTvSynopsis = (TextView) view.findViewById(R.id.tv_review);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);

        fillTextByMovie(movie, context);
    }

    private void fillTextByMovie(Movies movie, Context context) {
        URL url = MovieDatabase.buildImageString(movie.poster_path);
        if (url != null) {
            Picasso.with(context).load(url.toString()).into(mImagePoster);
        }
        mTvTitle.setText(movie.title);
        mTvRating.setText(movie.popularity);
        mTvSynopsis.setText(movie.overview);

        //mTvYear.setText(movie);
    }

}
