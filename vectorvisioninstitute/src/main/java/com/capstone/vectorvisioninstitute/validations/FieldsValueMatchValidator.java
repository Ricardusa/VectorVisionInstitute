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
            if(fieldValue.toString().startsWith("$2a")){
                return true; //ensure encrypted password passes field check
            }else {
                //do normal validation checking if not encrypted pwd
                return fieldValue.equals(fieldMatchValue);
            }
        }else {
            return fieldMatchValue == null;
        }
        /*right now with bCrypt we encrypt our password
        but when we try to do comparisons it tells us that
        our passwords do not match because we have now done a encrypt to the password
        if(fieldValue != null){
            return fieldValue.equals(fieldMatchValue);
        }else {
            return fieldMatchValue == null;
        }*/
    }

}
