package org.neophyte.popularmovie.popularmovie.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.neophyte.popularmovie.popularmovie.R;
import org.neophyte.popularmovie.popularmovie.network.pojo.Review;

/**
 * Created by juankarsten on 2/9/17.
 */

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private TextView mTvReviewer;
    private TextView mReview;

    public ReviewViewHolder(View itemView) {
        super(itemView);

        mTvReviewer = (TextView) itemView.findViewById(R.id.tv_reviewer);
        mReview = (TextView) itemView.findViewById(R.id.review);
    }


    public void bind(Review review) {
        mTvReviewer.setText("From: "+review.author);
        mReview.setText(review.content);
    }

}
