package com.uj.projects.booksplatform.review.service;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.book.repository.BookRepository;
import com.uj.projects.booksplatform.review.entity.Review;
import com.uj.projects.booksplatform.review.repository.ReviewRepository;
import com.uj.projects.booksplatform.user.entity.User;
import com.uj.projects.booksplatform.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ReviewServiceImplTest {
    private ReviewService sut;
    private ReviewRepository reviewRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;

    @BeforeEach
    public void SetUp(){
        reviewRepository = mock(ReviewRepository.class);
        bookRepository = mock(BookRepository.class);
        userRepository = mock(UserRepository.class);
        sut = new ReviewServiceImpl(reviewRepository, bookRepository, userRepository);
    }

    @Test
    public void shouldCallGetAllReviews(){
        // arrange
        List<Review> reviews = Any.listOf(Review.class);
        when(reviewRepository.findAll()).thenReturn(reviews);

        // act
        List actual = sut.getAll();

        // assert
        Assert.assertEquals(actual, reviews);
        verify(reviewRepository, atLeastOnce()).findAll();
    }

    @Test
    public void shouldCallSave(){
        // arrange
        Review review = Any.instanceOf(Review.class);
        when(reviewRepository.save(review)).thenReturn(review);

        // act
        Review actual = sut.create(review);

        // assert
        Assert.assertEquals(actual, review);
        verify(reviewRepository, atLeastOnce()).save(review);
    }

    @Test
    public void shouldCallDeleteById(){
        // arrange
        Integer id = Any.intValue();

        // act
        sut.delete(id);

        // assert
        verify(reviewRepository, atLeastOnce()).deleteById(id);
    }

    @Test
    public void shouldCallSaveWhileUpdate(){
        // arrange
        Review review = Any.instanceOf(Review.class);
        when(reviewRepository.save(review)).thenReturn(review);

        // act
        Review actual = sut.update(review);

        // assert
        Assert.assertEquals(actual, review);
        verify(reviewRepository, atLeastOnce()).save(review);
    }

    @Test
    public void shouldCallFindReviewsByBookWhileGetByBook(){
        // arrange
        Review review = Any.instanceOf(Review.class);
        Integer bookId = Any.intValue();
        Book book = Any.instanceOf(Book.class);
        List<Review> reviews = new ArrayList<>();
        reviews.add(review);
        when(reviewRepository.findReviewsByBook(book)).thenReturn(reviews);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // act
        List<Review> actual = sut.getByBookId(bookId);

        // assert
        Assert.assertEquals(actual, reviews);
        verify(reviewRepository, atLeastOnce()).findReviewsByBook(book);
        verify(bookRepository, atLeastOnce()).findById(bookId);
    }

    @Test
    public void shouldCallFindReviewsByUserWhileGetByUser(){
        // arrange
        Review review = Any.instanceOf(Review.class);
        Integer userId = Any.intValue();
        User user = Any.instanceOf(User.class);
        List<Review> reviews = new ArrayList<>();
        reviews.add(review);
        when(reviewRepository.findReviewsByUser(user)).thenReturn(reviews);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // act
        List<Review> actual = sut.getByUserId(userId);

        // assert
        Assert.assertEquals(actual, reviews);
        verify(reviewRepository, atLeastOnce()).findReviewsByUser(user);
        verify(userRepository, atLeastOnce()).findById(userId);
    }

    @Test
    public void shouldCallCountByBookWhileGetNumberOfReviews(){
        // arrange
        Review review = Any.instanceOf(Review.class);
        Integer bookId = Any.intValue();
        Integer numberOfReviews = Any.intValue();
        Book book = Any.instanceOf(Book.class);
        when(reviewRepository.countByBook(book)).thenReturn(numberOfReviews);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // act
        Integer actual = sut.getNumberOfReviewsByBookId(bookId);

        // assert
        Assert.assertEquals(actual, numberOfReviews);
        verify(reviewRepository, atLeastOnce()).countByBook(book);
        verify(bookRepository, atLeastOnce()).findById(bookId);
    }


}
