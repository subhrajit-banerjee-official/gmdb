package com.galvanize.gmdb.service;

import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.model.Review;
import com.galvanize.gmdb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public MovieService(){};

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

    public void addReview(Movie movie, Review review) {
        Movie updatedMovie = updateMovieReview(movie, review);
        movie = movieRepository.save(updatedMovie);
    }

    public Movie updateMovieReview(Movie movie, Review review) {
        StringBuffer sb = new StringBuffer();
        if(movie.getRating() != null){
            sb.append(movie.getRating());
            sb.append(",");
            sb.append(review.getRating());
        }else{
            sb.append(review.getRating());
        }
        movie.setRating(sb.toString());

        sb = new StringBuffer();
        if(movie.getReviews() != null){
            sb.append(movie.getReviews());
            sb.append(",");
            sb.append(review.getTextReview());
        }else{
            sb.append(review.getTextReview());
        }

        movie.setReviews(sb.toString());
        updateOverAllRating(movie);
        return movie;
    }

    public void updateOverAllRating(Movie movie) {
        if(movie.getRating()!= null && !movie.getRating().isBlank()){
            String[] values = movie.getRating().split(",");
            int sumOfAllRating = 0;
            for(String val : values){
                sumOfAllRating +=Integer.parseInt(val);
            }
            movie.setOverAllRating(sumOfAllRating/(values.length));
        }else{
            movie.setOverAllRating(0);
        }

    }

}
