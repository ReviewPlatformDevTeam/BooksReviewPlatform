package com.uj.projects.booksplatform.book.controller;

import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.book.service.BookService;
import com.uj.projects.booksplatform.book.dto.BookDto;
import com.uj.projects.booksplatform.book.mapper.BookMapper;
import com.uj.projects.booksplatform.review.service.ReviewService;
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
    private final ReviewService reviewService;

    @Autowired
    BookController(BookService bookService, BookMapper bookMapper, ReviewService reviewService){
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<BookDto> getBooks(){
        return bookService.getAllBooks().stream()
                .map(bookMapper::bookToBookDto)
                .map(this::mockNotExistingProperties).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") Integer id){
        Book book = bookService.getBookById(id);
        BookDto bookDto = bookMapper.bookToBookDto(book);
        return mockNotExistingProperties(bookDto);
    }

    @GetMapping(params = "search")
    public List<BookDto> searchBook(@RequestParam(name = "search") String title){
        return bookService.searchBook(title).stream().map(bookMapper::bookToBookDto).collect(Collectors.toList());
    }

    @PostMapping
    public BookDto createBook(@Valid @RequestBody BookDto bookDto){
        Book book = bookMapper.bookDtoToBook(bookDto);
        Book createdBook = bookService.createBook(book);
        return mockNotExistingProperties(bookMapper.bookToBookDto(createdBook));
    }

    @PostMapping("/{id}")
    public BookDto updateBook(@RequestBody BookDto bookDto, @PathVariable("id") Integer id){
        Book book = bookMapper.bookDtoToBook(bookDto);
        book.setId(id);
        Book updatedBook = bookService.updateBook(book);
        return mockNotExistingProperties(bookMapper.bookToBookDto(updatedBook));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Integer id){
        bookService.deleteBook(id);
    }

    private BookDto mockNotExistingProperties(BookDto bookDto){
        bookDto.setScore(5.0F);
        bookDto.setNumOfReviews(reviewService.getNumberOfReviewsByBookId(bookDto.getId()));
        return bookDto;
    }
}
