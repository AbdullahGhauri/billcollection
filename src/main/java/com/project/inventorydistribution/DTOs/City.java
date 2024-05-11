package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "City")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cityId")
    private long cityId;


    @Column(name = "cityName")
    private String cityName;
    @Column(name = "status")
    private String status;

    @JsonIgnore
    @OneToOne(mappedBy = "city")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "provinceId")
    private Province province;


}
