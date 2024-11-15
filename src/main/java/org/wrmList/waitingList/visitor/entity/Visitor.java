package org.wrmList.waitingList.visitor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Visitor")
@Table(name = "visitors")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 15, min = 2)
    @NotBlank
    private String name;
}
