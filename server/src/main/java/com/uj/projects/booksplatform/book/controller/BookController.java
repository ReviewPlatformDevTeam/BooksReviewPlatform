package com.uj.projects.booksplatform.book.controller;

import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.book.service.BookService;
import com.uj.projects.booksplatform.book.dto.BookDto;
import com.uj.projects.booksplatform.book.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @Autowired
    BookController(BookService bookService, BookMapper bookMapper){
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public List<BookDto> getBooks(){
        return bookService.getAllBooks().stream().map(bookMapper::bookToBookDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") Integer id){
        Book book = bookService.getBookById(id);
        return bookMapper.bookToBookDto(book);
    }

    @PostMapping
    public BookDto createBook(@Valid @RequestBody BookDto bookDto){
        Book book = bookMapper.bookDtoToBook(bookDto);
        Book createdBook = bookService.createBook(book);
        return bookMapper.bookToBookDto(createdBook);
    }

    @PostMapping("/{id}")
    public BookDto updateBook(@RequestBody BookDto bookDto, @PathVariable("id") Integer id){
        Book book = bookMapper.bookDtoToBook(bookDto);
        book.setId(id);
        Book updatedBook = bookService.updateBook(book);
        return bookMapper.bookToBookDto(updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Integer id){
        bookService.deleteBook(id);
    }
}
