package com.uj.projects.booksplatform.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class ReviewDto {

    private Integer id;

    @NotNull
    private String content;

    @Positive
    @Min(1)
    @Max(5)
    private Integer score;

    @NotNull
    private Integer book;

    @NotNull
    private Integer user;

}
