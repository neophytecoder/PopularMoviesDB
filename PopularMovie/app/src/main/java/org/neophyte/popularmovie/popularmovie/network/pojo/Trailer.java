package org.neophyte.popularmovie.popularmovie.network.pojo;

import java.net.URL;

/**
 * Created by juankarsten on 2/9/17.
 */

public class Trailer {
    public String id;
    public String name;
    public String key;
    public String site;
    public URL youtubeURL;

    public Trailer(String id, String name, String key) {
        this.id = id;
        this.name = name;
        this.key = key;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", site='" + site + '\'' +
                ", youtubeURL=" + youtubeURL +
                '}';
    }
}
