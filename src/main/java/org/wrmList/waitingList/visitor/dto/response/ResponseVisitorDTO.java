package org.wrmList.waitingList.visitor.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.wrmList.waitingList.visitorWaitingList.dto.response.EmbeddableVisitorWaitingListDTO;

import java.util.List;


public record ResponseVisitorDTO(
        @NotNull
        Long id,
        @Size(max = 15, min = 2)
        @NotBlank
        String name,
        List<EmbeddableVisitorWaitingListDTO> visitorWaitingLists
)
{}
