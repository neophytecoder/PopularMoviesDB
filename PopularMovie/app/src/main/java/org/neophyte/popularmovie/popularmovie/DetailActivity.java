package org.neophyte.popularmovie.popularmovie;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.neophyte.popularmovie.popularmovie.network.MovieDatabase;
import org.neophyte.popularmovie.popularmovie.network.TrailerDatabase;
import org.neophyte.popularmovie.popularmovie.network.pojo.Movies;
import org.neophyte.popularmovie.popularmovie.network.pojo.Trailer;
import org.neophyte.popularmovie.popularmovie.network.pojo.TrailerResult;
import org.neophyte.popularmovie.popularmovie.ui.TrailerAdapter;

import java.net.URL;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements TrailerAdapter.ListItemClickListener {
    private RecyclerView mRecyclerView;
    private Movies mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_trailers);
        TrailerAdapter adapter;

        if (getIntent().hasExtra(MainActivity.MOVIE_PARCEL)) {
            mMovie = getIntent().getParcelableExtra(MainActivity.MOVIE_PARCEL);

            adapter = new TrailerAdapter(getString(R.string.api_key), mMovie, this);
            mRecyclerView.setAdapter(adapter);

            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter.setListItemClickListener(this);
        }
    }

    @Override
    public void onClickItem(Trailer trailer) {
        Intent videoIntent = new Intent();
        videoIntent.setAction(Intent.ACTION_VIEW);
        videoIntent.setDataAndType(Uri.parse(trailer.youtubeURL.toString()), "video/*");
        Log.d(DetailActivity.class.getName(), Uri.parse(trailer.youtubeURL.toString()).toString());

        if (videoIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(videoIntent);
        } else {
            videoIntent = new Intent();
            videoIntent.setAction(Intent.ACTION_VIEW);
            videoIntent.setData(Uri.parse(trailer.youtubeURL.toString()));
            if (videoIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(videoIntent);
            }
            Log.d(DetailActivity.class.getName(), "No video app");
        }
    }
}
