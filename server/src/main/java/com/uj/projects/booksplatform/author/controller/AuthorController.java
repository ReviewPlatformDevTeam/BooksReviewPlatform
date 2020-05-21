package com.uj.projects.booksplatform.author.controller;

import com.uj.projects.booksplatform.author.entity.Author;
import com.uj.projects.booksplatform.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {


    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthors(){
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable("id") Integer id){
        return authorService.getById(id);
    }

    @PostMapping
    public Author createAuthor(@Valid @RequestBody Author author){
        return authorService.create(author);
    }

    @PostMapping("/{id}")
    public Author updateAuthor(@RequestBody Author author, @PathVariable("id") Integer id){
        return authorService.update(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") Integer id){
        authorService.delete(id);
    }

}
