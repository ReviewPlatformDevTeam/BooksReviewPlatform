package com.uj.projects.booksplatform.book.mapper;

import com.uj.projects.booksplatform.author.entity.Author;
import com.uj.projects.booksplatform.book.dto.BookDto;
import com.uj.projects.booksplatform.book.entity.Book;
import com.uj.projects.booksplatform.category.entity.Category;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-27T20:04:17+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.5 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book bookDtoToBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setAuthor( bookDtoToAuthor( bookDto ) );
        book.setCategory( bookDtoToCategory( bookDto ) );
        book.setId( bookDto.getId() );
        book.setTitle( bookDto.getTitle() );
        if ( bookDto.getReleaseDate() != null ) {
            book.setReleaseDate( new SimpleDateFormat( "yyyy-MM-dd" ).format( bookDto.getReleaseDate() ) );
        }

        return book;
    }

    @Override
    public BookDto bookToBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setCategory( bookCategoryName( book ) );
        bookDto.setAuthor( bookAuthorId( book ) );
        bookDto.setId( book.getId() );
        bookDto.setTitle( book.getTitle() );
        try {
            if ( book.getReleaseDate() != null ) {
                bookDto.setReleaseDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse( book.getReleaseDate() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }

        return bookDto;
    }

    protected Author bookDtoToAuthor(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( bookDto.getAuthor() );

        return author;
    }

    protected Category bookDtoToCategory(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( bookDto.getCategory() );

        return category;
    }

    private String bookCategoryName(Book book) {
        if ( book == null ) {
            return null;
        }
        Category category = book.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer bookAuthorId(Book book) {
        if ( book == null ) {
            return null;
        }
        Author author = book.getAuthor();
        if ( author == null ) {
            return null;
        }
        Integer id = author.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
