package com.uj.projects.booksplatform.review.service;

import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.error.exception.NotFoundException;
import com.uj.projects.booksplatform.review.entity.Review;
import com.uj.projects.booksplatform.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void delete(Integer id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Review update(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review getById(Integer id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(!review.isPresent()){
            throw new NotFoundException("Review with id: " + id + " not found");
        }
        return review.get();
    }

    @Override
    public List<Review> getByBook(Integer id) {
        Book book = new Book();
        book.setId(id);
        return reviewRepository.findReviewsByBook(book);
    }

    @Override
    public Integer getNumberOfReviewsByBook(Integer bookId) {
        return reviewRepository.countReviewsByBook(bookId);
    }
}
