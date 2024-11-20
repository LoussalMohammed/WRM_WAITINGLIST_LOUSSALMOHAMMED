package org.wrmList.waitingList.visitorWaitingList.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.time.DurationMin;
import org.wrmList.waitingList.util.enums.VisitorStatus;
import org.wrmList.waitingList.visitor.dto.EmbeddableVisitorDTO;
import org.wrmList.waitingList.visitorWaitingList.entity.embeddable.VisitKey;
import org.wrmList.waitingList.waitingList.dto.EmbeddableWaitingListDTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ResponseVisitorWaitingListDTO(
        EmbeddableVisitorDTO visitor,
        EmbeddableWaitingListDTO waitingList,
        @NotNull
        VisitKey id,

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
)
{}
