package com.uj.projects.booksplatform.category.service;

import com.uj.projects.booksplatform.category.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category create(Category category);
    void delete(Integer id);
    Category update(Category category);
    Category getById(Integer id);
}
