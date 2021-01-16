package com.galvanize.gmdb.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    private String textReview;
    private int rating;
}
