package org.wrmList.waitingList.waitingList.dto;

import jakarta.persistence.*;
import org.wrmList.waitingList.util.enums.OrderingStrategy;
import org.wrmList.waitingList.util.enums.ServiceTime;

import java.util.Set;

public record UpdateWaitingListDTO(

        java.time.@jakarta.validation.constraints.NotNull LocalDate date,

        @Enumerated(EnumType.STRING)
        OrderingStrategy orderingStrategy,

        @Enumerated(EnumType.STRING)
        ServiceTime serviceTime,

        int capacity
) {
}