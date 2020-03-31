package com.uj.projects.booksplatform.user.wrappers;

import com.uj.projects.booksplatform.user.resources.LoginResources;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtBuilderWrapperImpl implements JwtBuilderWrapper {
    @Override
    public String generateToken(String subject, long tokenLifeTimeInMinutes, String role, byte[] jwtSecret) {

        long currentTimeMillis = System.currentTimeMillis();
        long tokenLifetimeInMilliseconds = tokenLifeTimeInMinutes * 60000;
        return Jwts.builder()
                .setSubject(subject)
                .claim("roles", role)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + tokenLifetimeInMilliseconds))
                .signWith(SignatureAlgorithm.HS512, LoginResources.JWT_SECRET)
                .compact();
    }
}
