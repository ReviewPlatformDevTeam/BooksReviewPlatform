package com.uj.projects.booksplatform.review.service;

import com.uj.projects.booksplatform.review.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAll();
    Review create(Review review);
    void delete(Integer id);
    Review update(Review review);
    Review getById(Integer id);
    List<Review> getByBook(Integer id);
    List<Review> getByUser(Integer id);
    Integer getNumberOfReviewsByBook(Integer bookId);
}
