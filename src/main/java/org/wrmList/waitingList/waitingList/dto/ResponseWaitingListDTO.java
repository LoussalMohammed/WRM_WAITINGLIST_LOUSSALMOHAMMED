package org.wrmList.waitingList.waitingList.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.wrmList.waitingList.util.enums.DaysOfOperation;
import org.wrmList.waitingList.util.enums.OrderingStrategy;
import org.wrmList.waitingList.util.enums.ServiceTime;
import org.wrmList.waitingList.visitorWaitingList.dto.EmbeddableVisitorWaitingListDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record ResponseWaitingListDTO(

        @NotNull
        Long id,

        LocalDate date,

        @Enumerated(EnumType.STRING)
        OrderingStrategy orderingStrategy,

        @Enumerated(EnumType.STRING)
        ServiceTime serviceTime,

        @Min(1)
        int capacity,
        List<EmbeddableVisitorWaitingListDTO> visitorWaitingLists
)
{}
