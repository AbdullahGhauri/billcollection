package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "Province")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provinceId")
    private long provinceId;

    @Column(name = "provinceName")
    private String provinceName;
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "countryId")
    private Country country;
    @JsonIgnore
    @OneToOne(mappedBy = "province")
    private Address address;
    @JsonIgnore
    @OneToMany(mappedBy = "province",cascade = CascadeType.ALL)
    private List<City> cityList;
}
