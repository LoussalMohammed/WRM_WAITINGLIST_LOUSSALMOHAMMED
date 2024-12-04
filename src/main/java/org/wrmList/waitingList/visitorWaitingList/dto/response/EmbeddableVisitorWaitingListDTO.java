package org.wrmList.waitingList.visitorWaitingList.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.time.DurationMin;
import org.wrmList.waitingList.util.enums.VisitorStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record EmbeddableVisitorWaitingListDTO(
        LocalDateTime arrivalTime,

        @NotNull
        LocalTime startTime,

        @NotNull
        LocalTime endTime,

        @Enumerated(EnumType.STRING)
        VisitorStatus visitorStatus,

        @Min(0)
        Integer priority,

        @DurationMin(minutes = 2)
        Duration ept
) {
}
