package com.uj.projects.booksplatform.book.mapper;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.book.dto.BookDto;
import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.category.entity.Category;
import com.uj.projects.booksplatform.date.DateResources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookMapperTests {

    private BookMapper sut;

    @BeforeEach
    public void setUp(){
        sut = Mappers.getMapper( BookMapper.class );
    }

    @Test
    public void shouldBookToBookDto() throws ParseException {
        // arrange
        Book book = new Book();
        String bookTitle = Any.string();
        String bookAuthor = Any.string();
        String bookRelease = "2020-01-12";
        Date bookReleaseDate = new SimpleDateFormat(DateResources.dateFormat).parse(bookRelease);

        book.setTitle(bookTitle);
        book.setAuthor(bookAuthor);
        book.setReleaseDate(bookRelease);

        // act
        BookDto actual = sut.bookToBookDto(book);
        // assert
        Assert.assertEquals(actual.getTitle(), bookTitle);
        Assert.assertEquals(actual.getAuthor(), bookAuthor);
        Assert.assertEquals(actual.getReleaseDate(), bookReleaseDate);
    }

    @Test
    public void shouldBookDtoToBook() throws ParseException{
        // arrange
        BookDto bookDto = new BookDto();
        String bookTitle = Any.string();
        String bookAuthor = Any.string();
        String bookRelease = "2020-01-12";
        Date bookReleaseDate = new SimpleDateFormat(DateResources.dateFormat).parse(bookRelease);

        bookDto.setTitle(bookTitle);
        bookDto.setAuthor(bookAuthor);
        bookDto.setReleaseDate(bookReleaseDate);
        // act
        Book actual = sut.bookDtoToBook(bookDto);

        // assert
        Assert.assertEquals(actual.getTitle(), bookTitle);
        Assert.assertEquals(actual.getAuthor(), bookAuthor);
        Assert.assertEquals(actual.getReleaseDate(), bookRelease);
    }

    @Test
    public void shouldMapCategoryWhileMappingBookToBookDto(){
        // arrange
        Book book = new Book();
        String categoryName = Any.string();
        Category category = new Category(categoryName);
        book.setCategory(category);

        // act
        BookDto actual = sut.bookToBookDto(book);
        // assert
        Assert.assertEquals(actual.getCategory(), categoryName);
    }

    @Test
    public void shouldMapCategoryWhileMappingBookDtoToBook(){
        // arrange
        BookDto bookDto = new BookDto();
        String categoryName = Any.string();
        bookDto.setCategory(categoryName);
        // act
        Book actual = sut.bookDtoToBook(bookDto);

        // assert
        Assert.assertNotNull(actual.getCategory());
        Assert.assertEquals(actual.getCategory().getName(), categoryName);
    }
}
