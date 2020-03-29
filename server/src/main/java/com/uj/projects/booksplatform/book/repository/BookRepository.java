package com.uj.projects.booksplatform.book.repository;

import com.uj.projects.booksplatform.book.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
