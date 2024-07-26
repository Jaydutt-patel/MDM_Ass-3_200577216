package com.example.movie3.Model;

import java.util.List;

public class MovieResponse {
    private List<Movie> Search;
    public String response;

    public String getResponse() {
        return response;
    }

    public List<Movie> getSearch() {
        return Search;
    }
}
