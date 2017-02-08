package org.neophyte.popularmovie.popularmovie.network;

import java.util.Date;

/**
 * Created by juankarsten on 2/8/17.
 */

public class Movies {
    public String poster_path;
    public boolean adult;
    public String overview;
    public Date release_date;
    public String original_title;
    public String title;
    public String popularity;

    public Movies(String poster_path, boolean adult, String overview, Date release_date, String original_title, String title, String popularity) {
        this.poster_path = poster_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
        this.original_title = original_title;
        this.title = title;
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "poster_path='" + poster_path + '\'' +
                ", adult=" + adult +
                ", overview='" + overview + '\'' +
                ", release_date=" + release_date +
                ", original_title='" + original_title + '\'' +
                ", title='" + title + '\'' +
                ", popularity='" + popularity + '\'' +
                '}';
    }
}
