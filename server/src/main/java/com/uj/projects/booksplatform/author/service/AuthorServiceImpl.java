package com.uj.projects.booksplatform.author.service;

import com.uj.projects.booksplatform.author.entity.Author;
import com.uj.projects.booksplatform.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void delete(Integer id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author update(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author getById(Integer id) {
        return authorRepository.getOne(id);
    }

    @Override
    public List<Author> search(String name) {
        return authorRepository.findByNameContaining(name);
    }
}
