package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

@Entity
@Table(name = "Customer")
@Setter
@Getter
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
    @Column(name = "customerKey")
    private String customerKey;
    @Column(name = "customerStatus")
    private String customerStatus;
    @Column(name = "customerCreatedBy")
    private String customerCreatedBy;
    @Column(name = "customerCompanyName")
    private String customerCompanyName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerAddressId")
    private Address customerAddress;

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    private OrderDetail orderDetail;
}
