package org.wrmList.waitingList.visitor.dto.request;

import jakarta.validation.constraints.Size;

public record UpdateVisitorDTO(
        @Size(max = 15, min = 2)
        String name
) {
}
