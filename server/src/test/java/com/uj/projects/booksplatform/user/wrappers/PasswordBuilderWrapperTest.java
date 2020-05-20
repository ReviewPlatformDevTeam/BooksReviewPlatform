package com.uj.projects.booksplatform.user.wrappers;


import com.uj.projects.booksplatform.user.resources.PasswordResetResources;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PasswordBuilderWrapperTest {
    private PasswordBuilderWrapper sut;

    @BeforeEach
    public void SetUp(){
        sut = new PasswordBuilderWrapperImpl();
    }

    @Test
    public void shouldGenerateNewPassword(){
        // Act
        String actualNewPassword = sut.generatePassword();

        // Assert
        Assert.assertNotNull(actualNewPassword);
        Assert.assertTrue(actualNewPassword.length()>= PasswordResetResources.minLength);
        Assert.assertTrue(actualNewPassword.length()<= PasswordResetResources.maxLength);
    }

}