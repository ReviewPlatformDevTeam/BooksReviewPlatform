package com.uj.projects.booksplatform.book.service;

import com.uj.projects.booksplatform.book.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book createBook(Book book);
    void deleteBook(Integer id);
    Book updateBook(Book book);
    Book getBookById(Integer id);
    List<Book> searchBook(String title);
}
