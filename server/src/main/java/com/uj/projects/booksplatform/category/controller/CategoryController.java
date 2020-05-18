package com.uj.projects.booksplatform.category.controller;

import com.uj.projects.booksplatform.category.entity.Category;
import com.uj.projects.booksplatform.category.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @PostMapping
    public Category create(@Valid @RequestBody Category category){
        return categoryService.create(category);
    }
}
