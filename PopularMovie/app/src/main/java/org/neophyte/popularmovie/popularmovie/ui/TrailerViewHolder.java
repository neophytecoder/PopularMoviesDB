package org.neophyte.popularmovie.popularmovie.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.neophyte.popularmovie.popularmovie.R;
import org.neophyte.popularmovie.popularmovie.network.pojo.Movies;
import org.neophyte.popularmovie.popularmovie.network.pojo.Trailer;

/**
 * Created by juankarsten on 2/9/17.
 */

public class TrailerViewHolder extends RecyclerView.ViewHolder{
    TextView mTvTitle;

    public TrailerViewHolder(View itemView) {
        super(itemView);

        mTvTitle = (TextView) itemView.findViewById(R.id.tv_trailer_title);
    }

    public void bind(Trailer trailer) {
        mTvTitle.setText(trailer.name);
    }

}
