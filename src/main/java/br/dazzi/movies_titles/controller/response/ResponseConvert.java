package br.dazzi.movies_titles.controller.response;

import br.dazzi.movies_titles.controller.hackerrank.models.Movies;

import java.util.List;


public class ResponseConvert {

    private String[] titles;
    private List<Movies> movies;

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    private void cloneTitles(){
        for(int i = 0; i < titles.length; i++) {
            this.titles[i] = this.movies.get(i).getTitles();
        }
    }

    public ResponseConvert(List<Movies> movies) {
        this.movies = movies;
        this.titles = new String[movies.size()];
        this.cloneTitles();
    }
}
