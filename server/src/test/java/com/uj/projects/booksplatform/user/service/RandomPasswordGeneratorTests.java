package com.uj.projects.booksplatform.user.service;

import autofixture.publicinterface.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.*;

public class RandomPasswordGeneratorTests {

    private RandomPasswordGenerator sut;

    @BeforeEach
    public void SetUp(){
        sut = new RandomPasswordGeneratorImpl();
    }

    @Test
    public void ShouldGenerateDifferentPasswords(){
        // Arrange
        int length = Any.intValue();
        int numberOfPasswords = 10;
        List<String> passwords = new LinkedList<String>();

        // Act
        for (int i = 0; i < numberOfPasswords; i++){
            passwords.add(sut.generate(length));
        }

        // Assert
        Set<String> setOfPassword = new HashSet<String>(passwords);
        Assert.assertEquals(setOfPassword.size(), passwords.size());
    }

    @Test
    public void ShouldGeneratePasswordsOfDefaultSize(){
        // Arrange
        int expectedSize = 8;

        // Act
        String first = sut.generate();
        String second = sut.generate();

        // Assert
        Assert.assertNotEquals(first, second);
        Assert.assertEquals(first.length(), expectedSize);
        Assert.assertEquals(second.length(), expectedSize);
    }

}
