package org.wrmList.waitingList.shared.exception;

import org.springframework.http.HttpStatus;

import static org.wrmList.waitingList.shared.constant.ErrorCodes.INVALID_RELATIONSHIP;

public class InvalidRelationshipException extends BaseException {
    public InvalidRelationshipException(String message) {
        super(message, INVALID_RELATIONSHIP, HttpStatus.BAD_REQUEST);
    }
}
