package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressId")
    private long addressId;
    @Column(name = "addressDetail")
    private String addressDetail;
    @Column(name = "region")
    private String region;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cityId")
    private City city;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provinceId")
    private Province province;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "countryId")
    private Country country;

    @JsonIgnore
    @OneToOne(mappedBy = "customerAddress")
    private Customer customer;
    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Agent agent;



}
