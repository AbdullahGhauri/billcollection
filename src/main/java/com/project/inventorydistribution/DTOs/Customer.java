package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Customer")
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId")
    private long customerId;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "customerCNIC")
    private String customerCNIC;

    @Column(name = "customerContactNo")
    private String customerContactNo;

    @Column(name = "customerMonthlyFee")
    private String customerMonthlyFee;

    @Column(name = "customerConnectionDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate customerConnectionDate;


    @Column(name = "customerDueDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate  customerDueDate;

    @Column(name = "customerKey")
    private String customerKey;

    @Column(name = "customerStatus")
    private boolean customerStatus;

    @Column(name = "customerCreatedBy")
    private String customerCreatedBy;

    @Column(name = "customerCompanyName")
    private String customerCompanyName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerAddressId")
    private Address customerAddress;


    @JsonIgnore
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<BillCollection> billCollections;

}
