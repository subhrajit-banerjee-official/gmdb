package com.galvanize.gmdb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    private String title;
    private String director;
    private String actors;
    private int release_year;
    private String description;
    // Comma delimited Integer Values
    private String rating;
    // Comma delimited Integer Values
    private String reviews;
    private int overAllRating;
}
