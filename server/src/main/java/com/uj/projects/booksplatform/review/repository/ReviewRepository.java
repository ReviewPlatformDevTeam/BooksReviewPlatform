package com.uj.projects.booksplatform.review.repository;

import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.review.entity.Review;
import com.uj.projects.booksplatform.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> , ReviewCustomRepository {
    List<Review> findReviewsByBook(Book book);
    List<Review> findReviewsByUser(User user);
}
