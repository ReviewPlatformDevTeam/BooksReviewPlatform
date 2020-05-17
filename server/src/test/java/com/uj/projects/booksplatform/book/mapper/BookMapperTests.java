package com.uj.projects.booksplatform.book.mapper;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.book.dto.BookDto;
import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.category.entity.Category;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.testng.Assert;

public class BookMapperTests {

    @Test
    public void shouldMapCategory(){
        // arrange
        BookMapper sut = Mappers.getMapper( BookMapper.class );
        Book book = new Book();
        String categoryName = Any.string();
        Category category = new Category(categoryName);
        book.setCategory(category);

        // act
        BookDto actual = sut.bookToBookDto(book);

        // assert
        Assert.assertEquals(actual.getCategory(), categoryName);
    }
}
