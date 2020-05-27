package com.uj.projects.booksplatform.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class ReviewDto {

    private Integer id;

    private String content;

    private Integer score;

    private Integer book;

}
