package org.neophyte.popularmovie.popularmovie.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.neophyte.popularmovie.popularmovie.R;

/**
 * Created by juankarsten on 2/8/17.
 */

public class MovieImageViewHolder extends RecyclerView.ViewHolder {
    ImageView mImageView;

    public MovieImageViewHolder(View view) {
        super(view);
        mImageView = (ImageView) view.findViewById(R.id.movie_image);
    }

    public void bind(Context  context, String url) {
        Log.d(MovieImageViewHolder.class.getName(), url);
        Picasso.with(context)
                .load(url)
                .into(mImageView);
    }


}
