package com.uj.projects.booksplatform.user.service;

public interface RandomPasswordGenerator {
    String generate();
    String generate(int length);
}
