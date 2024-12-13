package org.wrmList.waitingList.shared.exception;

import org.springframework.http.HttpStatus;

import static org.wrmList.waitingList.shared.constant.ErrorCodes.INVALID_DATA;

// Invalid Data Exception
public class InvalidDataException extends BaseException {
    public InvalidDataException(String message) {
        super(message, INVALID_DATA, HttpStatus.BAD_REQUEST);
    }
}
