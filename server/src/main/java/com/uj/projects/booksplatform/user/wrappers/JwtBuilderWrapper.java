package com.uj.projects.booksplatform.user.wrappers;

public interface JwtBuilderWrapper {
    String generateToken(String subject, long tokenLifeTimeInMinutes, String role, byte[] jwtSecret);
}
