# MoviesTitles
consume other API, sort the movies titles and then return

Challenge
---
1. Query jsonmock.hackerrank
2. Initialize the titles array
3. sort titles in ascending order and return it as your answer

Solution
---

My solution was consume movies information from another endpoint. The result is added in ArrayList at Cache Class
until I get all movies, because External endpoint response is limited for 10 movies per page.

```java
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
```

After I finished the loop, in order to consume all movies from every page. 
```java
for (int page = 1; page <= response.getTotalPages()+1; page++){
            this.getMoviesTitlesPerPages(
                            substr,
                            page
            ).forEach(cache.getMovies()::add);
        }
```

I call a function getMovieSortByYearAsc() that was implemented using Java Comparator. It'll return a list of titles in ascending order.
```java
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
```


 finally, converting it to String[]
 
 ```java
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
```
 
and then return it.
```java
return new ResponseEntity<>(
                new ResponseConvert(cache.getMovieSortByYearAsc()).getTitles(),
                HttpStatus.OK
        );
```
