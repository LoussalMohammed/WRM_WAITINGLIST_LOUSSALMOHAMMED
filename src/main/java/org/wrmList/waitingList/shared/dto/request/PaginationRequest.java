package org.wrmList.waitingList.shared.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PaginationRequest(
        @Min(0)
        @NotNull
        Integer page,

        @Min(1)
        @Max(100) // Prevent excessive page sizes
        @NotNull
        Integer size,

        String sortBy,
        String sortDirection
) {
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_SIZE = 20;

    public static PaginationRequest of(Integer page, Integer size, String sortBy, String sortDirection) {
        return new PaginationRequest(
                page != null ? page : DEFAULT_PAGE,
                size != null ? size : DEFAULT_SIZE,
                sortBy,
                sortDirection
        );
    }
}