package org.neophyte.popularmovie.popularmovie.network.pojo;

import java.util.List;

/**
 * Created by juankarsten on 2/9/17.
 */

public class TrailerResult {
    public String id;
    public List<Trailer> results;

    public TrailerResult(String id, List<Trailer> results) {
        this.id = id;
        this.results = results;
    }

    @Override
    public String toString() {
        return "TrailerResult{" +
                "id='" + id + '\'' +
                ", results=" + results +
                '}';
    }
}
