package org.neophyte.popularmovie.popularmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.neophyte.popularmovie.popularmovie.network.MovieDatabase;
import org.neophyte.popularmovie.popularmovie.ui.MovieImageAdapater;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_list);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);

        // TODO MovieImageAdapater
        MovieImageAdapater movieImageAdapater = new MovieImageAdapater(getString(R.string.api_key));
        mRecyclerView.setAdapter(movieImageAdapater);
    }
}
