package com.uj.projects.booksplatform.user.wrappers;

import com.uj.projects.booksplatform.user.resources.PasswordResetResources;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;

@Service
public class PasswordBuilderWrapperImpl implements PasswordBuilderWrapper {

    @Override
    public String generatePassword() {
        RandomStringGenerator stringGenerator = new RandomStringGenerator.Builder().withinRange(PasswordResetResources.possibleCharacters).build();
        return stringGenerator.generate(PasswordResetResources.minLength, PasswordResetResources.maxLength);
    }
}
