package com.example.code.recyclerview;

public class Movie {
    private String movieName;
    private String movieGenre;
    private String year;

    public Movie(String movieName, String movieGenre, String year) {
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.year = year;
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
