package org.wrmList.waitingRoom.shared.dto;

import java.time.LocalDateTime;

public record ErrorDTO(
        String message,
        String errorCode,
        LocalDateTime timestamp
)
{}