package com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.entity;

import com.modulesixassesment.assesment.domain.enums.AffiliatedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Table(name = "affiliated")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AffiliatedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long affiliatedId;

    @Column(unique=true, nullable=false)
    private String document;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double salary;
    @Column(nullable = false)
    private LocalDate dateAffiliated;
    @Enumerated(EnumType.STRING)
    private AffiliatedStatus status;
}
