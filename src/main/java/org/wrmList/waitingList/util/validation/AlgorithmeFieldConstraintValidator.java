package org.wrmList.waitingList.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wrmList.waitingList.shared.exception.AlgorithmValidationException;
import org.wrmList.waitingList.shared.exception.ResourceNotFoundException;
import org.wrmList.waitingList.util.annotation.ValidateAlgorithmeFields;
import org.wrmList.waitingList.util.enums.OrderingStrategy;
import org.wrmList.waitingList.waitingList.entity.WaitingList;
import org.wrmList.waitingList.waitingList.repository.WaitingListRepository;

import java.lang.reflect.Field;
import java.time.Duration;

@Slf4j
@Component
public class AlgorithmeFieldConstraintValidator implements ConstraintValidator<ValidateAlgorithmeFields, Object> {

    @Autowired
    private WaitingListRepository waitingListRepository;
    
    private String wlIdField;
    private String pfField;
    private String eptField;

    @Override
    public void initialize(ValidateAlgorithmeFields constraintAnnotation) {
        this.wlIdField = constraintAnnotation.waitingListIdField();
        this.pfField = constraintAnnotation.priorityField();
        this.eptField = constraintAnnotation.estimatedProcessingTimeField();
        log.info("Validator initialized with fields: {}, {}, {}", wlIdField, pfField, eptField);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        try {
            Field waitingListIdOBJ = value.getClass().getDeclaredField(wlIdField);
            Field priorityOBJ = value.getClass().getDeclaredField(pfField);
            Field estimatedProcessingTimeOBJ = value.getClass().getDeclaredField(eptField);

            waitingListIdOBJ.setAccessible(true);
            priorityOBJ.setAccessible(true);
            estimatedProcessingTimeOBJ.setAccessible(true);

            Long waitingListId = (Long) waitingListIdOBJ.get(value);
            Integer priority = (Integer) priorityOBJ.get(value);
            Duration estimatedProcessingTime = (Duration) estimatedProcessingTimeOBJ.get(value);

            WaitingList waitingList;
            try {
                waitingList = waitingListRepository.findByIdOrThrow(waitingListId);
            } catch (ResourceNotFoundException e) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        "Waiting list with id " + waitingListId + " not found"
                ).addConstraintViolation();
                return false;
            }

            OrderingStrategy algo = waitingList.getOrderingStrategy();
            Boolean isValid = switch (algo) {
                case FIFO -> priority == null && estimatedProcessingTime == null;
                case HPF -> priority != null && estimatedProcessingTime == null;
                case STF -> priority == null && estimatedProcessingTime != null;
            };

            if (!isValid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        switch (algo) {
                            case FIFO -> "Ordering strategy is FIFO, priority and EPT must be null";
                            case HPF -> "Ordering strategy is HPF, priority must be set and EPT must be null";
                            case STF -> "Ordering strategy is STF, EPT must be set and priority must be null";
                        }
                ).addConstraintViolation();
                return false;
            }

            return true;
        } catch (AlgorithmValidationException | NoSuchFieldException | IllegalAccessException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Invalid DTO structure: " + e.getMessage()
            ).addConstraintViolation();
            return false;
        }
    }
}