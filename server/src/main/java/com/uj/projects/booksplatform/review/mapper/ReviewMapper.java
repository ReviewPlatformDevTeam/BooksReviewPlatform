package com.uj.projects.booksplatform.review.mapper;

import com.uj.projects.booksplatform.review.dto.ReviewDto;
import com.uj.projects.booksplatform.review.entity.Review;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReviewMapper {
    @Mapping(source = "book.id", target = "book")
    @Mapping(source = "user.id", target = "user")
    ReviewDto reviewToReviewDto(Review review);

    @Mapping(source = "book", target = "book.id")
    @Mapping(source = "user", target = "user.id")
    Review reviewDtoToReview(ReviewDto reviewDto);
}
