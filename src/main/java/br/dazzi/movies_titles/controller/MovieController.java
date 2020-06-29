package br.dazzi.movies_titles.controller;


import br.dazzi.movies_titles.controller.hackerrank.HackerRankAddress;
import br.dazzi.movies_titles.controller.hackerrank.models.Movies;
import br.dazzi.movies_titles.controller.hackerrank.models.Response;
import br.dazzi.movies_titles.controller.response.ResponseConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("movies/search")
@CrossOrigin(value = "*")
public class MovieController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{substr}")
    ResponseEntity<String[]> getMoviesTitles(@PathVariable String substr){
        Cache cache = new Cache();
        Response response = restTemplate.getForObject(
                HackerRankAddress.URL + String.format(
                        "?Title=%s",
                        substr
                ),
                Response.class);

        for (int page = 1; page <= response.getTotalPages()+1; page++){
            this.getMoviesTitlesPerPages(
                            substr,
                            page
            ).forEach(cache.getMovies()::add);
        }

        return new ResponseEntity<>(
                new ResponseConvert(cache.getMovieSortByYearAsc()).getTitles(),
                HttpStatus.OK
        );
    }

    Set<Movies> getMoviesTitlesPerPages(String substr, int pageNumber){
        Response response = restTemplate.getForObject(
                HackerRankAddress.URL +
                        String.format(
                                "?Title=%s&page=%s",
                                substr,
                                pageNumber
                        ),
                Response.class);

        return response.getData();
    }
}
