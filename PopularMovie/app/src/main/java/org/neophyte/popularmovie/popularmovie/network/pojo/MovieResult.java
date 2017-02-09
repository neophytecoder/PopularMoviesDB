package org.neophyte.popularmovie.popularmovie.network.pojo;

import java.util.List;

/**
 * Created by juankarsten on 2/8/17.
 */

public class MovieResult {
    public int page;
    public List<Movies> results;

    public MovieResult(int page, List<Movies> results) {
        this.page = page;
        this.results = results;
    }

    @Override
    public String toString() {
        return "MovieResult{" +
                "page=" + page +
                ", results=" + results +
                '}';
    }
}
