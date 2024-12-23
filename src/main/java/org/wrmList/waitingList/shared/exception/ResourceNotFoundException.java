package org.wrmList.waitingList.shared.exception;

import org.springframework.http.HttpStatus;
import static org.wrmList.waitingList.shared.constant.ErrorCodes.RESOURCE_NOT_FOUND;

// Resource Not Found
public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException(String message) {
        super(message, RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}