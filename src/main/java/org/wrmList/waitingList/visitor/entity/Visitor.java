package org.wrmList.waitingList.visitor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;

import java.util.ArrayList;
import java.util.List;

// Visitor Entity

@Entity(name = "Visitor")
@Table(name = "visitors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "visitorWaitingLists")
@EqualsAndHashCode(exclude = "visitorWaitingLists")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 15, min = 2)
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisitorWaitingList> visitorWaitingLists = new ArrayList<>();

}
