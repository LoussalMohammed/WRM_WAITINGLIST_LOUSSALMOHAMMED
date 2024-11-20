package org.wrmList.waitingList.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.wrmList.waitingList.util.annotation.Unique;

@Slf4j
public class UniqueConstraintValidator implements ConstraintValidator<Unique, String> {
    
    private Class<?> entityClass;
    private String fieldName;

    @Override
    public void initialize(Unique unique) {
        this.entityClass = unique.entityClass();
        this.fieldName = unique.field();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.info("trying validation");
        try {
            log.info("checking uniqueness");
            if (!UniquenessValidator.isValueUnique(entityClass, fieldName, value)) {
                log.info("not unique");
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                    String.format("%s with %s '%s' already exists", 
                            entityClass.getSimpleName(), fieldName, value)
                ).addConstraintViolation();
                return false;
            }
            log.info("unique");
            return true;
        } catch (Exception e) {
            log.error("Error during uniqueness validation: ", e);
            return false;
        }
    }
}
