package org.wrmList.waitingList.visitorWaitingList.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VisitKey implements Serializable {
    @NotNull
    @Column(name = "VISITOR_ID")
    private Long visitorId;
    @NotNull
    @Column(name = "WAITING_LIST_ID")
    private Long waitingListId;
}
