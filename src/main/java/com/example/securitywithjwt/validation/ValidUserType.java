package com.example.securitywithjwt.validation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
@Constraint(validatedBy = UserTypeValidation.class)
public @interface ValidUserType {
    public String message() default "Invalid UserType: It should be either Permanent Or Visitor";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
