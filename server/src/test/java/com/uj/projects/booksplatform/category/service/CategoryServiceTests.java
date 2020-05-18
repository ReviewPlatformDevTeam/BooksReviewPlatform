package com.uj.projects.booksplatform.category.service;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.category.entity.Category;
import com.uj.projects.booksplatform.category.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.List;

import static org.mockito.Mockito.*;

public class CategoryServiceTests {

    private CategoryService sut;
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp(){
        categoryRepository = mock(CategoryRepository.class);
        sut = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void shouldCallFindAll(){
        // arrange
        List<Category> categories = Any.listOf(Category.class);
        when(categoryRepository.findAll()).thenReturn(categories);

        // act
        List actual = sut.getAll();

        // assert
        Assert.assertEquals(actual, categories);
        verify(categoryRepository, atLeastOnce()).findAll();
    }

    @Test
    public void shouldCallSave(){
        // arrange
        Category category = Any.instanceOf(Category.class);
        when(categoryRepository.save(category)).thenReturn(category);

        // act
        Category actual = sut.create(category);

        // assert
        Assert.assertEquals(actual, category);
        verify(categoryRepository, atLeastOnce()).save(category);
    }

    @Test
    public void shouldCallDeleteById(){
        // arrange
        Integer id = Any.intValue();

        // act
        sut.delete(id);

        // assert
        verify(categoryRepository, atLeastOnce()).deleteById(id);
    }

    @Test
    public void shouldCallSaveWhileUpdate(){
        // arrange
        Category category = Any.instanceOf(Category.class);
        when(categoryRepository.save(category)).thenReturn(category);

        // act
        Category actual = sut.update(category);

        // assert
        Assert.assertEquals(actual, category);
        verify(categoryRepository, atLeastOnce()).save(category);
    }

}
