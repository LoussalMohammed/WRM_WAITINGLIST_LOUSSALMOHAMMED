package org.wrmList.waitingList.shared.dto.response;

import java.time.LocalDateTime;

public record ErrorDTO(
        String message,
        Throwable cause,
        String errorCode,
        LocalDateTime timestamp
)
{}