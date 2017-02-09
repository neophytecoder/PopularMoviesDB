package org.neophyte.popularmovie.popularmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.neophyte.popularmovie.popularmovie.network.MovieDatabase;
import org.neophyte.popularmovie.popularmovie.network.pojo.Movies;
import org.neophyte.popularmovie.popularmovie.ui.MovieImageAdapater;

public class MainActivity extends AppCompatActivity implements MovieImageAdapater.ListItemClickListener {
    private RecyclerView mRecyclerView;

    public static final String MOVIE_PARCEL = "Movie";
    private MovieImageAdapater mMovieImageAdapater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_list);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);

        // TODO MovieImageAdapater
        mMovieImageAdapater = new MovieImageAdapater(getString(R.string.api_key), this);
        mRecyclerView.setAdapter(mMovieImageAdapater);
    }

    @Override
    public void onClickItem(Movies aMovie) {
        Log.d(MainActivity.class.getName(), "Click " + aMovie);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(MOVIE_PARCEL, aMovie);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.popularity:
                mMovieImageAdapater.changeSortByCriteria(MovieDatabase.POPULARITY);
                return true;
            case R.id.top_rated:
                mMovieImageAdapater.changeSortByCriteria(MovieDatabase.TOP_RATED);
                return true;
            default:
                return false;
        }
    }
}
