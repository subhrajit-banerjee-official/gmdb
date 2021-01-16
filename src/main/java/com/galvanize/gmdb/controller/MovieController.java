package com.galvanize.gmdb.controller;

import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> fetchAllMovies(){
        List<Movie> movies = new ArrayList<>();
        movies=movieService.fetchAllMovies();

        return movies;

    }

    @GetMapping("/movies/{title}")
    public ResponseEntity fetchSpecificMovie(@PathVariable String title){
        Movie movie;
        movie=movieService.fetchSpecificMovie(title);
        if(movie !=null){
            return new ResponseEntity(movie, HttpStatus.OK);
        }else{
            return new ResponseEntity ("Movie doesn't exist", HttpStatus.NO_CONTENT);
        }

    }

}
