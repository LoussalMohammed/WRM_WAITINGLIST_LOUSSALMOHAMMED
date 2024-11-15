package org.wrmList.waitingList.visitor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateVisitorDTO(
        @Size(max = 15, min = 2)
        @NotBlank
        String name
) {
}
