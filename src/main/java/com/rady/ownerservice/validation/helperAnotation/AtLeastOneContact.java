package com.rady.ownerservice.validation.helperAnotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AtLeastOneContactValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneContact {

    String message() default "Either email or phone must be provided.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
