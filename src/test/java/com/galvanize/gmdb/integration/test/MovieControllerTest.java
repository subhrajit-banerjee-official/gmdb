package com.galvanize.gmdb.integration.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.gmdb.TestUtility;
import com.galvanize.gmdb.model.GMDBConstants;
import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.model.Review;
import com.galvanize.gmdb.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class MovieControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MovieRepository movieRepository;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void setupDb() {
        List<Movie> movies = new ArrayList<>();
        Movie gladiatorMovie = TestUtility.getMovie(GMDBConstants.EXPECTED_VALUE_MOVIE_TITLE_GLADIATOR);
        movies.add(gladiatorMovie);
        movies.add(TestUtility.getMovie("Titanic"));

        when(movieRepository.findAll()).thenReturn(movies);
        when(movieRepository.findById(GMDBConstants.EXPECTED_VALUE_MOVIE_TITLE_GLADIATOR)).thenReturn(java.util.Optional.ofNullable(gladiatorMovie));
        when(movieRepository.findById(GMDBConstants.NO_SUCH_MOVIE)).thenReturn(java.util.Optional.empty());
    }

    @Test
    public void test_FetchAllMovies() throws Exception {
        mvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].title").value(GMDBConstants.EXPECTED_VALUE_MOVIE_TITLE_GLADIATOR));
    }

    @Test
    public void test_FetchSpecificMovies_Success() throws Exception {
        mvc.perform(get("/movies/Gladiator"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(GMDBConstants.EXPECTED_VALUE_MOVIE_TITLE_GLADIATOR))
                .andExpect(jsonPath("$.director").value(GMDBConstants.EXPECTED_VALUE_DIRECTOR))
                .andExpect(jsonPath("$.actors").value(GMDBConstants.EXPECTED_VALUE_ACTORS))
                .andExpect(jsonPath("$.release_year").value(GMDBConstants.EXPECTED_VALUE_RELEASE_YEAR))
                .andExpect(jsonPath("$.description").value(GMDBConstants.EXPECTED_VALUE_DESCRIPTION))
                .andExpect(jsonPath("$.reviews").value(GMDBConstants.EXPECTED_VALUE_REVIEWS))
                .andExpect(jsonPath("$.rating").value(GMDBConstants.EXPECTED_VALUE_RATING))
                .andExpect(jsonPath("$.overAllRating").value(GMDBConstants.EXPECTED_VALUE_OVERALL_RATING));

    }

    @Test
    public void test_FetchSpecificMovies_NoSuchMovie() throws Exception {
        mvc.perform(get("/movies/NoSuchMovie"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").value("Movie doesn't exist"));
    }

    @Test
    public void test_reviewSpecificMovies_Success() throws Exception {
        Review review = new Review();
        review.setTextReview("Greatest movie of all time");
        review.setRating(5);
        String reviewString = mapper.writeValueAsString(review);
        mvc.perform(post("/movies/Gladiator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reviewString))
                .andExpect(status().isOk());
    }

}
