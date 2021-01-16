package com.galvanize.gmdb.unit.test;

import com.galvanize.gmdb.TestUtility;
import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.model.Review;
import com.galvanize.gmdb.service.MovieService;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class MovieServiceTest {

    @Test
    public void testUpdateMovieReview(){
        Movie movie = TestUtility.getMovie("Catch Me if you can",null, null);
        Review review = TestUtility.getReview();
        MovieService movieService = new MovieService();
        assertNull(movie.getRating());

        Movie updatedMovie=movieService.updateMovieReview(movie, review);
        assertEquals(review.getRating().toString(), updatedMovie.getRating());
        assertEquals(review.getTextReview(), updatedMovie.getReviews());

        updatedMovie=movieService.updateMovieReview(updatedMovie, review);
        assertEquals("5,5", updatedMovie.getRating());
        assertEquals("Greatest movie of all time,Greatest movie of all time", updatedMovie.getReviews());

    }

    @Test
    public void testUpdateOverAllRating(){
        Movie movie = TestUtility.getMovie("Catch Me if you can",null, "5,3");
        MovieService movieService = new MovieService();
        movieService.updateOverAllRating(movie);
        assertEquals(4, movie.getOverAllRating());
    }

    @Test
    public void testAvailabilityOfReview(){
        MovieService movieService = new MovieService();
        boolean emptyReview = movieService.validateRequest(TestUtility.getReviewWithoutRating());
        assertEquals(false, emptyReview);

        boolean validReview = movieService.validateRequest(TestUtility.getReview());
        assertEquals(true, validReview);
    }
}
