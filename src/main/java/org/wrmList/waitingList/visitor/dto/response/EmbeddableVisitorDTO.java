package org.wrmList.waitingList.visitor.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmbeddableVisitorDTO(
        @Size(max = 15, min = 2)
        @NotBlank
        String name
)
{}
