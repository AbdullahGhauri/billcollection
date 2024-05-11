package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "ScreenTab")
public class ScreenTab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screenTabId")
    private long screenTabId;

    @Column(name = "screenTabName")
    private String screenTabName;
    @Column(name = "status")
    private String status;
    @Column(name = "entryBy")
    private String entryBy;
    @Column(name = "dateTime")
    private String dateTime;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;


    @JsonIgnore
    @OneToMany(mappedBy = "screenTab",cascade = CascadeType.ALL)
    private List<SubScreen> subScreenList;
}
