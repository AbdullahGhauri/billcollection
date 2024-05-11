package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departmentId")
    private long departmentId;

    @Column(name = "departmentName")
    private String departmentName;
    @Column(name = "status")
    private String status;
    @Column(name = "entryBy")
    private String entryBy;
    @Column(name = "dateTime")
    private String dateTime;

    @JsonIgnore
    @OneToOne(mappedBy = "department")
    private Agent agent;
}
