package org.wrmList.waitingList.util.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.wrmList.waitingList.util.validation.IdExistsConstraintValidator;

import java.lang.annotation.*;

// Id Exists Annotation
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdExistsConstraintValidator.class)
@Documented
public @interface IdExists {
    String message() default "Entity not found";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> entityClass();
    String field();
}
