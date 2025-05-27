package com.clothes.clothes.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidation implements ConstraintValidator<PhoneNumber, String>{
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("^\\+52\\d{10}$");   
    }
}
