package org.wrmList.waitingList.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

// Base Exception
@Getter
public abstract class BaseException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus status;

    protected BaseException(String message, String errorCode, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

}