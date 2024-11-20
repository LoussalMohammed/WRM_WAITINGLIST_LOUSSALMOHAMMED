package org.wrmList.waitingList.visitor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;

import java.util.ArrayList;
import java.util.List;

public record CreateVisitorDTO(
        @Size(max = 15, min = 2)
        @NotBlank
        String name,
        List<VisitorWaitingList> visitorWaitingListLists
)
{}
