package com.uj.projects.booksplatform.book.service;

import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.book.repository.BookRepository;
import com.uj.projects.booksplatform.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    @Autowired
    BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if(!book.isPresent()){
           throw new NotFoundException("Book with id: " + id + " not found");
        }
        return book.get();
    }

    @Override
    public List<Book> searchBook(String title) {
        return bookRepository.findByTitleContaining(title);
    }
}
