package com.uj.projects.booksplatform.review.service;

import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.book.repository.BookRepository;
import com.uj.projects.booksplatform.error.exception.NotFoundException;
import com.uj.projects.booksplatform.review.entity.Review;
import com.uj.projects.booksplatform.review.repository.ReviewRepository;
import com.uj.projects.booksplatform.user.entity.User;
import com.uj.projects.booksplatform.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    private BookRepository bookRepository;

    private UserRepository userRepository;

    ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository, UserRepository userRepository){
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
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
        Optional<Book> book = bookRepository.findById(id);
        if(!book.isPresent()){
            throw new NotFoundException("Book with id: " + id + " not found");
        }
        return reviewRepository.findReviewsByBook(book.get());
    }

    @Override
    public List<Review> getByUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new NotFoundException("User with id: " + id + " not found");
        }
        return reviewRepository.findReviewsByUser(user.get());
    }

    @Override
    public Integer getNumberOfReviewsByBook(Integer bookId) {
        return reviewRepository.countReviewsByBook(bookId);
    }
}
