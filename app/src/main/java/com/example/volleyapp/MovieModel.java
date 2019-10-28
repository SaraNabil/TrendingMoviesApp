package com.example.volleyapp;

public class MovieModel {
    private int id ;
    private String movieName;
    private String movieImageLink;
    private double popularity;

    public MovieModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieImageLink() {
        return movieImageLink;
    }

    public void setMovieImageLink(String movieImageLink) {
        this.movieImageLink = movieImageLink;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
