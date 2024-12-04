package org.wrmList.waitingList.waitingList.dto.request;

import jakarta.persistence.*;
import org.wrmList.waitingList.util.enums.OrderingStrategy;
import org.wrmList.waitingList.util.enums.ServiceTime;

public record UpdateWaitingListDTO(

        java.time.@jakarta.validation.constraints.NotNull LocalDate date,

        @Enumerated(EnumType.STRING)
        OrderingStrategy orderingStrategy,

        @Enumerated(EnumType.STRING)
        ServiceTime serviceTime,

        int capacity
) {
}
