package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "Country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "countryId")
    private long countryId;

    @Column(name = "countryName")
    private String countryName;
    @Column(name = "status")
    private String status;
    @JsonIgnore
    @OneToOne(mappedBy = "country")
    private Address address;
    @JsonIgnore
    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL)
    private List<Province> provinceList;
}
