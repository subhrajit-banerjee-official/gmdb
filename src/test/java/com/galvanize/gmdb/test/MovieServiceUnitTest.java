package com.galvanize.gmdb.test;

import com.galvanize.gmdb.TestUtility;
import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.model.Review;
import com.galvanize.gmdb.service.MovieService;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class MovieServiceUnitTest {

    public static final String TITLE_CATCH_ME_IF_YOU_CAN = "Catch Me if you can";

    @Test
    public void testUpdateMovieReview(){
        Movie movie = TestUtility.getMovie(TITLE_CATCH_ME_IF_YOU_CAN,null, null);
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
        MovieService movieService = new MovieService();

        Movie movieNoRating = TestUtility.getMovie(TITLE_CATCH_ME_IF_YOU_CAN,null, null);
        movieService.updateOverAllRating(movieNoRating);
        assertEquals(0, movieNoRating.getOverAllRating());

        Movie movie = TestUtility.getMovie(TITLE_CATCH_ME_IF_YOU_CAN,null, "5,3");
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
