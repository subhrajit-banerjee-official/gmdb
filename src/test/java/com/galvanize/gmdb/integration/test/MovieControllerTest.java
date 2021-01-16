package com.galvanize.gmdb.integration.test;

import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.repository.MovieRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
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
    private MockMvc mvc;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    public void test_FetchAllMovies() throws Exception {
        mvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].title").value("Gladiator"));
    }

    @Test
    public void test_FetchSpecificMovies_Success() throws Exception {
        mvc.perform(get("/movies/Gladiator"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Gladiator"));
        ;
    }

    @BeforeEach
    private void setupDb() {
        List<Movie> movies = new ArrayList<>();
        Movie gladiatorMovie = getMovie("Gladiator");
        movies.add(gladiatorMovie);
        movies.add(getMovie("Titanic"));

        when(movieRepository.findAll()).thenReturn(movies);

        when(movieRepository.findById("Gladiator")).thenReturn(java.util.Optional.ofNullable(gladiatorMovie));
    }

    private Movie getMovie(String title) {
        Movie movie = Movie.builder()
                .title(title)
                .director("Rajnikant")
                .actors("Russle,Ram,Subhrajit")
                .release_year(2001)
                .description("A movie about something")
                .build();
        return movie;
    }

}
