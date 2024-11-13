package org.wrmList.waitingRoom.shared.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.wrmList.waitingRoom.shared.dto.ErrorDTO;
import org.wrmList.waitingRoom.shared.exception.InvalidRelationshipException;
import org.wrmList.waitingRoom.shared.exception.ResourceNotFoundException;
import org.wrmList.waitingRoom.shared.exception.ValidationException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static org.wrmList.waitingRoom.shared.constant.ErrorCodes.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorDTO resourceNotFoundHandler(ResourceNotFoundException re) {
        return new ErrorDTO(
                re.getMessage(),
                re.getErrorCode(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(Exception.class)
    public ErrorDTO globalExceptionHandler(Exception e) {
        return new ErrorDTO(
                e.getMessage(),
                GENERAL_ERROR,
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ErrorDTO validationExceptionHandler(ValidationException ve) {
        return new ErrorDTO(
                ve.getMessage(),
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

        return new ErrorDTO(message, CONSTRAINT_VIOLATION, LocalDateTime.now());
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

        return new ErrorDTO(message, VALIDATION_ERROR,LocalDateTime.now());
    }

    @ExceptionHandler(InvalidRelationshipException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleInvalidRelationshipException(InvalidRelationshipException irx) {
        return new ErrorDTO(
                irx.getMessage(),
                irx.getErrorCode(),
                LocalDateTime.now()
        );
    }

}
