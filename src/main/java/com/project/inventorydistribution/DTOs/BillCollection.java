package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "BillCollection")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billCollectionId")
    private long billCollectionId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agentId")
    private Agent agent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @Column(name = "billInvoiceDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate billInvoiceDate;

    @Column(name = "amountReceived")
    private double amountReceived;

    @Column(name = "billStatus")
    private boolean billStatus;

}
