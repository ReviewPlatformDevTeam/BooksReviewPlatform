package com.uj.projects.booksplatform.book.controller;

import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable("id") Integer id){
        return bookRepository.findById(id).get();
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PostMapping("/books/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable("id") Integer id){
        book.setId(id);
        return bookRepository.save(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable("id") Integer id){
        Book book = new Book();
        book.setId(id);
        bookRepository.delete(book);
    }
}
