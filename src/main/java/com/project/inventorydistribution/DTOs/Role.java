package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private long roleId;
    @Column(name = "roleName")
    private String roleName;
    @Column(name = "status")
    private String status;
    @Column(name = "entryBy")
    private String entryBy;
    @Column(name = "dateTime")
    private String dateTime;

    @JsonIgnore
    @OneToOne(mappedBy = "role")
    private Agent agent;

    @JsonIgnore
    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<ScreenTab> screenTabList;
}
