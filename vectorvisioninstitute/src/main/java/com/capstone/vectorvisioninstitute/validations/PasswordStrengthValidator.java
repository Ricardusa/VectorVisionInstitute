package com.capstone.vectorvisioninstitute.validations;

import com.capstone.vectorvisioninstitute.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

//TODO: video 157
public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {

    List<String> weakPasswords;

    @Override
    public void initialize(PasswordValidator passwordValidator){
        weakPasswords = Arrays.asList("12345", "password", "qwerty");

    }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext cxt){
        return passwordField != null && (!weakPasswords.contains(passwordField));
    }
}
