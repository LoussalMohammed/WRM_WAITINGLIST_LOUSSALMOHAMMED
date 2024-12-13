package org.wrmList.waitingList.visitor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;

import java.util.List;

// Create Visitor DTO

public record CreateVisitorDTO(
        @Size(max = 15, min = 2)
        @NotBlank
        String name,
        List<VisitorWaitingList> visitorWaitingListLists
)
{}
