package org.neophyte.popularmovie.popularmovie.network.pojo;

import java.util.List;

/**
 * Created by juankarsten on 2/9/17.
 */

public class ReviewResult {
    public int total_pages;
    public int total_results;
    public String id;

    public List<Review> results;

    public ReviewResult(int total_pages, int total_results, String id, List<Review> results) {
        this.total_pages = total_pages;
        this.total_results = total_results;
        this.id = id;
        this.results = results;
    }

    @Override
    public String toString() {
        return "ReviewResult{" +
                "total_pages=" + total_pages +
                ", total_results=" + total_results +
                ", id='" + id + '\'' +
                ", results=" + results +
                '}';
    }
}
