package org.wrmList.waitingList.visitorWaitingList.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.time.DurationMin;
import org.wrmList.waitingList.util.annotation.ValidateAlgorithmeFields;
import org.wrmList.waitingList.util.enums.VisitorStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

// Update Visitor Waiting List DTO

@ValidateAlgorithmeFields
public record UpdateVisitorWaitingListDTO(
        LocalDateTime arrivalTime,

        LocalTime startTime,

        LocalTime endTime,

        @Enumerated(EnumType.STRING)
        VisitorStatus visitorStatus,

        @Min(0)
        Integer priority,

        @DurationMin(minutes = 2)
        Duration ept,
        @NotNull
        Long visitorId,
        @NotNull
        Long waitingListId
) {
}
