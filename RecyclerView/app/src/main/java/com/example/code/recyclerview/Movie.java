package com.example.code.recyclerview;

public class Movie {
    private String moviePosterUrl;
    private String movieName;
    private String movieGenre;
    private String year;

    public Movie(String moviePosterUrl, String movieName, String movieGenre, String year) {
        this.moviePosterUrl = moviePosterUrl;
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.year = year;
    }

    public String getMoviePosterUrl() {
        return moviePosterUrl;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public String getYear() {
        return year;
    }
}
