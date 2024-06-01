package com.project.inventorydistribution.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.inventorydistribution.DTOs.Agent;
import com.project.inventorydistribution.DTOs.BillCollection;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillCollectionResponse {

    private BillCollection billCollection;
    private List<BillCollection> billCollectionList;
    private ErrorResponse errorResponse;

    public BillCollectionResponse(BillCollection billCollection) {this.billCollection = billCollection;}
    public BillCollectionResponse(List<BillCollection> billCollectionList) {this.billCollectionList = billCollectionList;}
    public BillCollectionResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
