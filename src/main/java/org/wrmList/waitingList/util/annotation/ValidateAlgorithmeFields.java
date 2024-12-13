package org.wrmList.waitingList.util.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.wrmList.waitingList.util.validation.AlgorithmeFieldConstraintValidator;

import java.lang.annotation.*;

// Validate Algorithme Fields
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AlgorithmeFieldConstraintValidator.class)
@Documented
public @interface ValidateAlgorithmeFields {
    String message() default "Invalid field for selected algorithm";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String waitingListIdField() default "waitingListId";
    String priorityField() default "priority";
    String estimatedProcessingTimeField() default "ept";
}
