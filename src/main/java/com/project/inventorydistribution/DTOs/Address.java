package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Address")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressId")
    private long addressId;
    @Column(name = "addressDetail")
    private String addressDetail;
    @Column(name = "region")
    private String region;



    @JsonIgnore
    @OneToOne(mappedBy = "customerAddress")
    private Customer customer;
    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Agent agent;



}
