package com.uj.projects.booksplatform.book.service;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.book.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.List;

import static org.mockito.Mockito.*;

public class BookServiceTests {

    private BookService sut;
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp(){
        bookRepository = mock(BookRepository.class);
        sut = new BookServiceImpl(bookRepository);
    }

    @Test
    public void shouldCallGetAllBooks(){
        // arrange
        List<Book> books = Any.listOf(Book.class);
        when(bookRepository.findAll()).thenReturn(books);

        // act
        List actual = sut.getAllBooks();

        // assert
        Assert.assertEquals(actual, books);
        verify(bookRepository, atLeastOnce()).findAll();
    }

    @Test
    public void shouldCallSave(){
        // arrange
        Book book = Any.instanceOf(Book.class);
        when(bookRepository.save(book)).thenReturn(book);

        // act
        Book actual = sut.createBook(book);

        // assert
        Assert.assertEquals(actual, book);
        verify(bookRepository, atLeastOnce()).save(book);
    }

    @Test
    public void shouldCallDeleteById(){
        // arrange
        Integer id = Any.intValue();

        // act
        sut.deleteBook(id);

        // assert
        verify(bookRepository, atLeastOnce()).deleteById(id);
    }

    @Test
    public void shouldCallSaveWhileUpdate(){
        // arrange
        Book book = Any.instanceOf(Book.class);
        when(bookRepository.save(book)).thenReturn(book);

        // act
        Book actual = sut.updateBook(book);

        // assert
        Assert.assertEquals(actual, book);
        verify(bookRepository, atLeastOnce()).save(book);
    }

}
