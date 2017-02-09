package org.neophyte.popularmovie.popularmovie.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.neophyte.popularmovie.popularmovie.R;
import org.neophyte.popularmovie.popularmovie.network.ReviewDatabase;
import org.neophyte.popularmovie.popularmovie.network.TrailerDatabase;
import org.neophyte.popularmovie.popularmovie.network.pojo.Movies;
import org.neophyte.popularmovie.popularmovie.network.pojo.Review;
import org.neophyte.popularmovie.popularmovie.network.pojo.Trailer;

import java.util.List;

/**
 * Created by juankarsten on 2/9/17.
 */

public class TrailerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements TrailerDatabase.OnResult, ReviewDatabase.OnResult{
    private List<Trailer> mTrailers;
    private List<Review> mReviews;
    private Context mContext;
    private Movies mMovie;
    private ListItemClickListener mListItemClickListener;

    private static final int HEADER_VIEW_TYPE = 0;
    private static final int TRAILER_VIEW_TYPE = 1;
    private static final int HEADER_REVIEW_VIEW_TYPE = 2;
    private static final int REVIEW_VIEW_TYPE = 3;

    public TrailerAdapter(String apiKey, Movies movie, Context context) {
        mContext = context;
        mMovie = movie;
        TrailerDatabase mTrailerDB = new TrailerDatabase(apiKey, movie.id, this);
        ReviewDatabase mReviewDatabase = new ReviewDatabase(apiKey, movie.id, this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        if (viewType == HEADER_VIEW_TYPE) {
            Log.d(TrailerAdapter.class.getName(), "header");
            View view = inflater.inflate(R.layout.trailer_header_view_item, parent, false);
            DetailHeaderViewHolder viewHolder = new DetailHeaderViewHolder(view, mMovie, mContext);
            return viewHolder;
        } else if (viewType == REVIEW_VIEW_TYPE) {
            Log.d(TrailerAdapter.class.getName(), "reviews");
            View view = inflater.inflate(R.layout.review_list_item, parent, false);
            ReviewViewHolder viewHolder = new ReviewViewHolder(view);
            return viewHolder;
        } else if (viewType == HEADER_REVIEW_VIEW_TYPE) {
            Log.d(TrailerAdapter.class.getName(), "reviews header");
            View view = inflater.inflate(R.layout.review_header_view_item, parent, false);
            return new RecyclerView.ViewHolder(view) {};
        }

        Log.d(TrailerAdapter.class.getName(), "list");
        View view = inflater.inflate(R.layout.trailer_list_item, parent, false);
        final TrailerViewHolder viewHolder = new TrailerViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition() - 1;
                if (pos>=0 && pos < mTrailers.size()) {
                    mListItemClickListener.onClickItem(mTrailers.get(pos));
                }
            }
        });

        return viewHolder;
    }

    public void setListItemClickListener(ListItemClickListener mListItemClickListener) {
        this.mListItemClickListener = mListItemClickListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        position = position - 1;
        if (mTrailers != null && mTrailers.size()>position && position >= 0  ) {
            Log.d(TrailerAdapter.class.getName(), "onBindViewHolder " + position + mTrailers.get(position) );
            TrailerViewHolder trailerViewHolder = (TrailerViewHolder) holder;
            trailerViewHolder.bind(mTrailers.get(position));
        } else if (mReviews != null && mReviews.size()+mTrailers.size()+1>position && position >= mTrailers.size()+1  ) {
            position = position - mTrailers.size() -1 ;
            Log.d(TrailerAdapter.class.getName(), "onBindReviewHolder " + position + mReviews.get(position) );
            ReviewViewHolder viewHolder = (ReviewViewHolder) holder;
            viewHolder.bind(mReviews.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mTrailers == null) {
            return 1;
        }
        int size = mTrailers.size() + 1;
        if (mReviews != null) {
            size += mReviews.size();
        }
        size += 1;
        return  size;
    }

    @Override
    public void onResult(List<Trailer> trailers) {
        mTrailers = trailers;
        notifyDataSetChanged();
    }

    @Override
    public void onReviewResult(List<Review> results) {
        mReviews = results;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_VIEW_TYPE;
        } else if (position > 0 && position < mTrailers.size() + 1) {
            return TRAILER_VIEW_TYPE;
        } else if (position == mTrailers.size() + 1) {
            return HEADER_REVIEW_VIEW_TYPE;
        }
        return REVIEW_VIEW_TYPE;
    }

    public interface ListItemClickListener {
        void onClickItem(Trailer trailer);
    }


}
