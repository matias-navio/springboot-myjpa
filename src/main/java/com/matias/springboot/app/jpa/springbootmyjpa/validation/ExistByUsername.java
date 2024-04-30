package com.matias.springboot.app.jpa.springbootmyjpa.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistByUsernameValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistByUsername {

    String message() default "ya está registrado en la base de datos!";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
