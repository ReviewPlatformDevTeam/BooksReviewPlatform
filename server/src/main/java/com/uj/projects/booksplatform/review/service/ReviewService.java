package com.uj.projects.booksplatform.review.service;

import com.uj.projects.booksplatform.review.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAll();
    Review create(Review review);
    void delete(Integer id);
    Review update(Review review);
    Review getById(Integer id);
    List<Review> getByBookId(Integer id);
    List<Review> getByUserId(Integer id);
    Integer getNumberOfReviewsByBookId(Integer bookId);
}
