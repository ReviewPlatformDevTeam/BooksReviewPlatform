package com.uj.projects.booksplatform.book.mapper;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.book.dto.BookDto;
import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.category.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.testng.Assert;

import javax.validation.constraints.NotBlank;

public class BookMapperTests {

    private BookMapper sut;

    @BeforeEach
    public void setUp(){
        sut = Mappers.getMapper( BookMapper.class );
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
