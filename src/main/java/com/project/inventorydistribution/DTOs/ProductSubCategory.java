package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "ProductSubCategory")
public class ProductSubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productSubCategoryId")
    private long productSubCategoryId;

    @Column(name = "productSubCategoryName")
    private String productSubCategoryName;
    @Column(name = "status")
    private String status;
    @Column(name = "entryBy")
    private String entryBy;
    @Column(name = "dateTime")
    private String dateTime;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;


    @JsonIgnore
    @OneToMany(mappedBy = "productSubCategory", cascade = CascadeType.ALL)
    private List<ProductPrice> productPriceList;
    @JsonIgnore
    @OneToOne(mappedBy = "productSubCategory")
    private OrderDetail orderDetail;
}
