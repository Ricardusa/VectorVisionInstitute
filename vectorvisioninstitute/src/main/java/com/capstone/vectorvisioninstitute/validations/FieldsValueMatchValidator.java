package com.capstone.vectorvisioninstitute.validations;

import com.capstone.vectorvisioninstitute.annotation.FieldsValueMatch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

//object since we are going to this validation for two fields
public class FieldsValueMatchValidator implements
        ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation){
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context){
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        if(fieldValue != null){
            return fieldValue.equals(fieldMatchValue);
        }else {
            return fieldMatchValue == null;
        }

    }
}
