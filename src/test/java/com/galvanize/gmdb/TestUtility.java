package com.galvanize.gmdb;

import com.galvanize.gmdb.model.GMDBConstants;
import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.model.Review;

public class TestUtility {

    //Utility Method to build movie object
    public static Movie getMovie(String title, String reviews, String rating) {
        Movie movie = Movie.builder()
                .title(title)
                .director(GMDBConstants.EXPECTED_VALUE_DIRECTOR)
                .actors(GMDBConstants.EXPECTED_VALUE_ACTORS)
                .release_year(GMDBConstants.EXPECTED_VALUE_RELEASE_YEAR)
                .description(GMDBConstants.EXPECTED_VALUE_DESCRIPTION)
                .rating(rating)
                .reviews(reviews)
                .overAllRating(GMDBConstants.EXPECTED_VALUE_OVERALL_RATING)
                .build();
        return movie;
    }

    public static Review getReview(){
        Review review = new Review();
        review.setTextReview(GMDBConstants.EXPECTED_VALUE_TITANIC_REVIEWS);
        review.setRating(GMDBConstants.EXPECTED_VALUE_TITANIC_RATING);
        return review;
    }
}
