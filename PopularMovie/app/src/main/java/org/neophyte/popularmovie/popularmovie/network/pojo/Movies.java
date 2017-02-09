package org.neophyte.popularmovie.popularmovie.network.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by juankarsten on 2/8/17.
 */

public class Movies implements Parcelable{
    public String poster_path;
    public boolean adult;
    public String overview;
    public Date release_date;
    public String original_title;
    public String title;
    public String popularity;
    public String id;

    public Movies(String id, String poster_path, boolean adult, String overview, Date release_date, String original_title, String title, String popularity) {
        this.poster_path = poster_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
        this.original_title = original_title;
        this.title = title;
        this.popularity = popularity;
        this.id = id;
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
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(poster_path);
        dest.writeBooleanArray(new boolean[]{adult});
        dest.writeString(overview);
        dest.writeString(release_date.toString());
        dest.writeString(original_title);
        dest.writeString(title);
        dest.writeString(popularity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            String id = source.readString();
            String poster_path = source.readString();
            boolean[] adults = new boolean[1];
            source.readBooleanArray(adults);
            boolean adult = adults[0];
            String overview = source.readString();
            String date = source.readString();
            String originalTitle = source.readString();
            String title = source.readString();
            String popularity = source.readString();
            return new Movies(id, poster_path, adult, overview, null, originalTitle, title, popularity);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[0];
        }
    };
}
