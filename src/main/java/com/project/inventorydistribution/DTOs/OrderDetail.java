package com.project.inventorydistribution.DTOs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "OrderDetail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderDetailId")
    private long orderDetailId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @Column(name = "visitDateTime")
    private String visitDateTime;
    @Column(name = "visitDateTimeSys")
    private String visitDateTimeSys;
    @Column(name = "noOfOrder")
    private String noOfOrder;
    @Column(name = "status")
    private String status;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "image")
    private String image;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    private Product product;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productSubCategoryId")
    private ProductSubCategory productSubCategory;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productPriceId")
    private ProductPrice productPrice;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agentId")
    private Agent agent;
}
