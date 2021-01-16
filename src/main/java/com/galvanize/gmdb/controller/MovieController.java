package com.galvanize.gmdb.controller;

import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.model.Review;
import com.galvanize.gmdb.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/movies")
    public List<Movie> fetchAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movies = movieService.fetchAllMovies();

        return movies;

    }

    @GetMapping("/movies/{title}")
    public ResponseEntity fetchSpecificMovie(@PathVariable String title) {
        Movie movie;
        movie = movieService.fetchSpecificMovie(title);
        if (movie != null) {
            return new ResponseEntity(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity("Movie doesn't exist", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/movies/{title}")
    public ResponseEntity reviewSpecificMovie(@PathVariable String title, @RequestBody Review review) {
        return new ResponseEntity("Updated", HttpStatus.OK);
    }
}
