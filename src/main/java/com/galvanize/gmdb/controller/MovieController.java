package com.galvanize.gmdb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @GetMapping("/movies")
    public List<String> fetchAllMovies(){
        List<String> movies = new ArrayList<>();
        movies.add("Catch me if you can");
        movies.add("Gladiator");

        return movies;

    }

}
