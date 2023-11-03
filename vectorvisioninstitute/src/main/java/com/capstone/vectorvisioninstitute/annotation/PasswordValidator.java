package com.capstone.vectorvisioninstitute.annotation;

import com.capstone.vectorvisioninstitute.validations.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Increase Password Strength";
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
