package com.uj.projects.booksplatform.review.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewCustomRepository {
        @Query(value = "SELECT count(r) FROM Review r WHERE r.book.id = :bookId")
        Integer countReviewsByBook(@Param("bookId") Integer bookId);
}
