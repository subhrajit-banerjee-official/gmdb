package com.galvanize.gmdb.controller;

import com.galvanize.gmdb.model.GMDBConstants;
import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.model.Review;
import com.galvanize.gmdb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> fetchAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movies = movieService.fetchAllMovies();

        return movies;

    }

    @GetMapping("/movies/{title}")
    public ResponseEntity fetchSpecificMovie(@PathVariable String title) {
        Movie movie = movieService.fetchSpecificMovie(title);
        if (movie != null) {
            return new ResponseEntity(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity(GMDBConstants.ERR_MOVIE_DOES_NOT_EXIST, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/movies/{title}")
    public ResponseEntity reviewSpecificMovie(@PathVariable String title, @RequestBody Review review) {
        if (movieService.validateRequest(review)) {
            Movie movie = movieService.fetchSpecificMovie(title);
            //UpdateMovie With Review
            if (movie != null) {
                movieService.addReview(movie, review);
                return new ResponseEntity(movie, HttpStatus.OK);
            } else {
                return new ResponseEntity(GMDBConstants.ERR_MOVIE_DOES_NOT_EXIST, HttpStatus.NO_CONTENT);
            }

        } else {
            return new ResponseEntity(GMDBConstants.ERR_STAR_RATING_REQUIRED, HttpStatus.BAD_REQUEST);

        }
    }


}
