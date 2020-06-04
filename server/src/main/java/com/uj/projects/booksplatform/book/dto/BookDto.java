package com.uj.projects.booksplatform.book.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uj.projects.booksplatform.date.DateResources;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Data
@AllArgsConstructor@NoArgsConstructor
public class BookDto {
    private Integer id;

    @NotBlank
    private String title;

    @NotNull
    private Integer author;

    @NotBlank
    private String category;

    @NotBlank
    private String description;

    @DateTimeFormat(pattern = DateResources.dateFormat)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateResources.dateFormat, timezone = JsonFormat.DEFAULT_TIMEZONE)
    private Date releaseDate;

    @NotNull
    @PositiveOrZero
    private Float score;

    @NotNull
    @PositiveOrZero
    private Integer numOfReviews;

    String photoUrl;
}
