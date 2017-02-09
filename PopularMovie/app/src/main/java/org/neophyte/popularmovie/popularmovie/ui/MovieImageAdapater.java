package org.neophyte.popularmovie.popularmovie.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.neophyte.popularmovie.popularmovie.R;
import org.neophyte.popularmovie.popularmovie.network.MovieDatabase;
import org.neophyte.popularmovie.popularmovie.network.pojo.Movies;

import java.net.URL;
import java.util.List;

/**
 * Created by juankarsten on 2/8/17.
 */

public class MovieImageAdapater extends RecyclerView.Adapter<MovieImageViewHolder> implements MovieDatabase.OnResult {
    private MovieDatabase mMovieDatabase;
    private List<Movies> mMovies;
    private Context mContext;
    private ListItemClickListener mListItemClickListener;

    public MovieImageAdapater(String apiKey, ListItemClickListener listItemClickListener) {
        init(apiKey);
        mListItemClickListener = listItemClickListener;
    }

    private void init(String apiKey) {
        if (mMovieDatabase == null) {
            mMovieDatabase = new MovieDatabase(apiKey);
            mMovieDatabase.setOnResult(this);
        }
    }

    @Override
    public MovieImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.image_list_item, parent, false);
        final MovieImageViewHolder holder = new MovieImageViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                Movies aMovie = mMovies.get(adapterPosition);
                mListItemClickListener.onClickItem(aMovie);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieImageViewHolder holder, int position) {
        String imagePath = mMovies.get(position).poster_path;
        URL posterPath = MovieDatabase.buildImageString(imagePath);
        if (posterPath != null) {
            holder.bind(mContext, posterPath.toString());

            if (position == getItemCount() - 1) {
                mMovieDatabase.nextPage();
                mMovieDatabase.reinit();
            }
        }

    }

    @Override
    public int getItemCount() {
        if (mMovies == null) {
            return 0;
        }
        return mMovies.size();
    }

    @Override
    public void onResult(List<Movies> movies) {
        this.mMovies = movies;
        notifyDataSetChanged();
    }

    public void changeSortByCriteria(int criteria) {
        mMovieDatabase.changeSortCriteria(criteria);
    }


    public interface ListItemClickListener {
        public void onClickItem(Movies movie);
    }
}
