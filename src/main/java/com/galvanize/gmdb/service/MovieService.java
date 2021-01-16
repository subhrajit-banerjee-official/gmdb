package com.galvanize.gmdb.service;

import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MovieService {

    MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> fetchAllMovies() {
        return movieRepository.findAll();
    }

    public Movie fetchSpecificMovie(String title) {
        Movie movie;
        try{
            movie = movieRepository.findById(title).get();
        }catch(NoSuchElementException nse){
            movie = null;
        }
        return movie;
    }
}
