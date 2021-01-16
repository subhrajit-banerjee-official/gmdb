package com.galvanize.gmdb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping("/movies")
    public void fetchAllMovies(){


    }

}
