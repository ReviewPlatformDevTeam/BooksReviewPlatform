package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.user.entity.LoginResult;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
public class JwtLoginService implements LoginService {

    private static final int tokenLifetimeInMilliseconds = 20000;

    @Override
    public LoginResult Login(String username, String password) {

        String encodedString = CreateEncodedString(password);
        String token = CreateToken(username, encodedString);
        return new LoginResult(true, token);
    }

    private static String CreateEncodedString(String textToEncode){
       return Base64.getEncoder().encodeToString(textToEncode.getBytes());
    }

    private static String CreateToken(String username, String encodedString){
        long currentTimeMillis = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(username)
                .claim("roles","user")
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + tokenLifetimeInMilliseconds))
                .signWith(SignatureAlgorithm.HS512, encodedString)
                .compact();
    }

}
