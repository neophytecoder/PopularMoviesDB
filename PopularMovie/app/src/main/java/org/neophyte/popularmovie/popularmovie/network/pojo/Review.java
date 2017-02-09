package org.neophyte.popularmovie.popularmovie.network.pojo;

/**
 * Created by juankarsten on 2/9/17.
 */

public class Review {
    public String id;
    public String author;
    public String content;
    public String url;

    public Review(String id, String author, String content, String url) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
