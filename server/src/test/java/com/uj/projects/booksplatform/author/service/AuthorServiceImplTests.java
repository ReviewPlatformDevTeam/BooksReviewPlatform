package com.uj.projects.booksplatform.author.service;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.author.entity.Author;
import com.uj.projects.booksplatform.author.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

public class AuthorServiceImplTests {

    private AuthorService sut;
    private AuthorRepository authorRepository;

    @BeforeEach
    public void SetUp(){
        authorRepository = mock(AuthorRepository.class);
        sut = new AuthorServiceImpl(authorRepository);
    }

    @Test
    public void shouldCallGetAllAuthors(){
        // arrange
        List<Author> authors = Any.listOf(Author.class);
        when(authorRepository.findAll()).thenReturn(authors);

        // act
        List actual = sut.getAll();

        // assert
        Assert.assertEquals(actual, authors);
        verify(authorRepository, atLeastOnce()).findAll();
    }

    @Test
    public void shouldCallSave(){
        // arrange
        Author author = Any.instanceOf(Author.class);
        when(authorRepository.save(author)).thenReturn(author);

        // act
        Author actual = sut.create(author);

        // assert
        Assert.assertEquals(actual, author);
        verify(authorRepository, atLeastOnce()).save(author);
    }

    @Test
    public void shouldCallDeleteById(){
        // arrange
        Integer id = Any.intValue();

        // act
        sut.delete(id);

        // assert
        verify(authorRepository, atLeastOnce()).deleteById(id);
    }

    @Test
    public void shouldCallSaveWhileUpdate(){
        // arrange
        Author author = Any.instanceOf(Author.class);
        when(authorRepository.save(author)).thenReturn(author);

        // act
        Author actual = sut.update(author);

        // assert
        Assert.assertEquals(actual, author);
        verify(authorRepository, atLeastOnce()).save(author);
    }

    @Test
    public void shouldCallFindByNameContainingWhileSearch(){
        // arrange
        String name = Any.string();
        Author author = Any.instanceOf(Author.class);
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        when(authorRepository.findByNameContaining(name)).thenReturn(authors);

        // act
        List<Author> actual = sut.search(name);

        // assert
        Assert.assertEquals(actual, authors);
        verify(authorRepository, atLeastOnce()).findByNameContaining(name);
    }

}
