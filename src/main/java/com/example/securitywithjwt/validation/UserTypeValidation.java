package com.example.securitywithjwt.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.*;
public class UserTypeValidation implements ConstraintValidator<ValidUserType,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> userType = Arrays.asList("Permanent", "Visitor");
        return userType.contains(value);
    }
}
