package org.wrmList.waitingList.waitingList.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import org.wrmList.waitingList.util.enums.OrderingStrategy;
import org.wrmList.waitingList.util.enums.ServiceTime;

import java.time.LocalDate;

public record CreateWaitingListDTO(
        LocalDate date,

        @Enumerated(EnumType.STRING)
        OrderingStrategy orderingStrategy,

        @Enumerated(EnumType.STRING)
        ServiceTime serviceTime,

        int capacity
)
{}
