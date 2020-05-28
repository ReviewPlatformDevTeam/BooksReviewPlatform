package com.uj.projects.booksplatform.author.service;

import com.uj.projects.booksplatform.author.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();
    Author create(Author author);
    void delete(Integer id);
    Author update(Author author);
    Author getById(Integer id);
}
