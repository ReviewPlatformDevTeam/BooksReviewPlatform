package com.uj.projects.booksplatform.book.mapper;

import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.book.dto.BookDto;
import com.uj.projects.booksplatform.date.DateResources;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {

    @Mapping(target="releaseDate", dateFormat = DateResources.dateFormat)
    @Mapping(source = "category", target = "category.name")
    @Mapping(source = "author", target = "author.id")
    Book bookDtoToBook(BookDto bookDto);

    @Mapping(target="releaseDate", dateFormat = DateResources.dateFormat)
    @Mapping(source = "category.name", target = "category")
    @Mapping(source = "author.id", target = "author")
    BookDto bookToBookDto(Book book);

}
