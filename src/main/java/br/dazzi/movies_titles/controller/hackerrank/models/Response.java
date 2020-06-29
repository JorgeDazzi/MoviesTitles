package br.dazzi.movies_titles.controller.hackerrank.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response {

    @JsonProperty(value="page")
    int page;

    @JsonProperty(value="per_page")
    int per_page;

    @JsonProperty(value="total")
    int total;

    @JsonProperty(value="total_pages")
    int totalPages;

    @JsonProperty(value="data")
    Set<Movies> data;
}
