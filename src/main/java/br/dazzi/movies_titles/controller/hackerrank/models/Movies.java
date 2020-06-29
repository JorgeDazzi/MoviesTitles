package br.dazzi.movies_titles.controller.hackerrank.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movies{

    @JsonProperty(value="Title")
    String titles;

    @JsonProperty(value="Year")
    int Year;

    @JsonProperty(value="imdbID")
    String imdbID;

}
