package com.galvanize.gmdb;

import com.galvanize.gmdb.model.GMDBConstants;
import com.galvanize.gmdb.model.Movie;

public class TestUtility {

    //Utility Method to build movie object
    public static Movie getMovie(String title) {
        Movie movie = Movie.builder()
                .title(title)
                .director(GMDBConstants.EXPECTED_VALUE_DIRECTOR)
                .actors(GMDBConstants.EXPECTED_VALUE_ACTORS)
                .release_year(GMDBConstants.EXPECTED_VALUE_RELEASE_YEAR)
                .description(GMDBConstants.EXPECTED_VALUE_DESCRIPTION)
                .rating(GMDBConstants.EXPECTED_VALUE_RATING)
                .reviews(GMDBConstants.EXPECTED_VALUE_REVIEWS)
                .overAllRating(GMDBConstants.EXPECTED_VALUE_OVERALL_RATING)
                .build();
        return movie;
    }
}
