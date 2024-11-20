package org.wrmList.waitingList.shared.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.wrmList.waitingList.shared.dto.ErrorDTO;
import org.wrmList.waitingList.shared.exception.InvalidRelationshipException;
import org.wrmList.waitingList.shared.exception.ResourceNotFoundException;
import org.wrmList.waitingList.shared.exception.ValidationException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static org.wrmList.waitingList.shared.constant.ErrorCodes.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorDTO resourceNotFoundHandler(ResourceNotFoundException re) {
        return new ErrorDTO(
                re.getMessage(),
                re.getCause(),
                re.getErrorCode(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(Exception.class)
    public ErrorDTO globalExceptionHandler(Exception e) {
        return new ErrorDTO(
                e.getMessage(),
                e.getCause(),
                GENERAL_ERROR,
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ErrorDTO validationExceptionHandler(ValidationException ve) {
        return new ErrorDTO(
                ve.getMessage(),
                ve.getCause(),
                ve.getErrorCode(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("Validation failed");

        return new ErrorDTO(message, new Throwable("data passed is invalid!"), CONSTRAINT_VIOLATION, LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    return fieldName + ": " + errorMessage;
                })
                .collect(Collectors.joining(", "));

        return new ErrorDTO(message, new Throwable("Method passed arguments are not valid!"),VALIDATION_ERROR,LocalDateTime.now());
    }

    @ExceptionHandler(InvalidRelationshipException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleInvalidRelationshipException(InvalidRelationshipException irx) {
        return new ErrorDTO(
                irx.getMessage(),
                irx.getCause(),
                irx.getErrorCode(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(NoSuchFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleNoSuchFieldException(NoSuchFieldException nfx) {
        return new ErrorDTO(
                nfx.getMessage(),
                nfx.getCause(),
                NO_SUCH_FILE,
                LocalDateTime.now()
        );
    }



}
