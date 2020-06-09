package com.uj.projects.booksplatform.author.repository;

import com.uj.projects.booksplatform.author.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByNameContaining(String name);

}
