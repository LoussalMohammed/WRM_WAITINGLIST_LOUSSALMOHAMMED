package org.wrmList.waitingList.waitingList.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.wrmList.waitingList.util.enums.OrderingStrategy;
import org.wrmList.waitingList.util.enums.ServiceTime;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "WaitingList")
@Table(name = "waitingLists")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "visitorWaitingLists")
@EqualsAndHashCode(exclude = "visitorWaitingLists")
public class WaitingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "orderingStrategy")
    private OrderingStrategy orderingStrategy;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_time")
    private ServiceTime serviceTime = ServiceTime.CONTINUOUSLY;

    @Column(name = "capacity")
    @NotNull
    @Min(1)
    private int capacity;

    @OneToMany(mappedBy = "waitingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisitorWaitingList> visitorWaitingLists = new ArrayList<>();
}
