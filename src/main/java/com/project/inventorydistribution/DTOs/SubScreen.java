package com.project.inventorydistribution.DTOs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "SubScreen")
public class SubScreen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subScreenId")
    private long subScreenId;


    @Column(name = "subScreenName")
    private String subScreenName;
    @Column(name = "status")
    private String status;
    @Column(name = "entryBy")
    private String entryBy;
    @Column(name = "dateTime")
    private String dateTime;

    @ManyToOne
    @JoinColumn(name = "screenTabId")
    private ScreenTab screenTab;
}
