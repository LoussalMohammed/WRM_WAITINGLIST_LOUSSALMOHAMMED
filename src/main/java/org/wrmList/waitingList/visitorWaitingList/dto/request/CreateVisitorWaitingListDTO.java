package org.wrmList.waitingList.visitorWaitingList.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.time.DurationMin;
import org.wrmList.waitingList.util.annotation.ValidateAlgorithmeFields;

import java.time.Duration;
import java.time.LocalDateTime;

public record  CreateVisitorWaitingListDTO(
        LocalDateTime arrivalTime,

        @Min(0)
        Integer priority,

        @DurationMin(minutes = 2)
        Duration ept,
        @NotNull
        Long visitorId,
        @NotNull
        Long waitingListId

)
{}
