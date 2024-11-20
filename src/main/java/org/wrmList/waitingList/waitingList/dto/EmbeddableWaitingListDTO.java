package org.wrmList.waitingList.waitingList.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import org.wrmList.waitingList.util.enums.OrderingStrategy;
import org.wrmList.waitingList.util.enums.ServiceTime;

import java.time.LocalDate;
import java.util.Set;

public record EmbeddableWaitingListDTO(
        LocalDate date,

        @Enumerated(EnumType.STRING)
        OrderingStrategy orderingStrategy,

        @Enumerated(EnumType.STRING)
        ServiceTime serviceTime,

        @Min(1)
        int capacity
) {
}
