package org.wrmList.waitingList.util.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.wrmList.waitingList.util.validation.UniqueConstraintValidator;

import java.lang.annotation.*;

// Unique
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueConstraintValidator.class)
@Documented
public @interface Unique {
    String message() default "Value must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> entityClass();
    String field();
}
