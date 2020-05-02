package com.uj.projects.booksplatform.user.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RandomPasswordGeneratorImpl implements RandomPasswordGenerator {

    private final static int defaultPasswordLength = 8;

    @Override
    public String generate() {
        return generate(defaultPasswordLength);
    }

    @Override
    public String generate(int length) {
        char[] possibleCharacters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?").toCharArray();
        String randomStr = RandomStringUtils.random( length, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom());
        return randomStr;
    }
}
