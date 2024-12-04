package org.wrmList.waitingList.visitorWaitingList.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.time.DurationMin;
import org.wrmList.waitingList.util.enums.VisitorStatus;
import org.wrmList.waitingList.visitor.entity.Visitor;
import org.wrmList.waitingList.waitingList.entity.WaitingList;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "VisitorWaitingList")
@Table(name = "visitorsWaitingLists")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"visitor", "waitingList"})
@EqualsAndHashCode(exclude = {"visitor", "waitingList"})
public class VisitorWaitingList {

    @EmbeddedId
    private VisitKey id;

    @Column(name = "arrivalTime")
    private LocalDateTime arrivalTime = LocalDateTime.now();

    @Column(name = "startTime")
    private LocalTime startTime = null;

    @Column(name = "endTime")
    private LocalTime endTime = null;


    @Enumerated(EnumType.STRING)
    @Column(name = "visitor_status")
    private VisitorStatus visitorStatus = VisitorStatus.WAITING;

    @Min(0)
    @Column(name = "priority")
    private Integer priority;

    @DurationMin(minutes = 2)
    @Column(name = "estimated_processing_time")
    private Duration ept;

    @MapsId("waitingListId")
    @ManyToOne
    @JoinColumn(name = "waiting_list_id")
    private WaitingList waitingList;

    @MapsId("visitorId")
    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;


}
