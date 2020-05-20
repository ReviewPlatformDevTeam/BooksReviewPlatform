package com.uj.projects.booksplatform.category.service;

import com.uj.projects.booksplatform.category.entity.Category;
import com.uj.projects.booksplatform.category.repository.CategoryRepository;
import com.uj.projects.booksplatform.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Integer id) {
        Optional<Category> book = categoryRepository.findById(id);
        if(!book.isPresent()){
            throw new NotFoundException("Category with id: " + id + " not found");
        }
        return book.get();
    }
}
