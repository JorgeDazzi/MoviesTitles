package br.dazzi.movies_titles.controller;

import br.dazzi.movies_titles.controller.hackerrank.models.Movies;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Data
public class Cache {
    private List<Movies> movies = new ArrayList<>();

    public List<Movies> getMovieSortByYearAsc(){
        Collections.sort(this.movies, Comparator.comparing(Movies::getTitles));
        return this.movies;
    }
    public List<Movies> getMovieSortByYearDesc(){
        Collections.sort(this.movies, Comparator.comparing(Movies::getTitles));
        return this.movies;
    }
}
