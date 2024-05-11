package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "ProductPrice")
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productPriceId")
    private long productPriceId;

    @Column(name = "productPrice")
    private String productPrice;
    @Column(name = "status")
    private String status;
    @Column(name = "entryBy")
    private String entryBy;
    @Column(name = "dateTime")
    private String dateTime;

    @ManyToOne
    @JoinColumn(name = "productSubCategoryId")
    private ProductSubCategory productSubCategory;

    @JsonIgnore
    @OneToOne(mappedBy = "productPrice")
    private OrderDetail orderDetail;
}
