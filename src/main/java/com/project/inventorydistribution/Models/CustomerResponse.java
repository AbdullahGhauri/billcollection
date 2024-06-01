package com.project.inventorydistribution.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.inventorydistribution.DTOs.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {

    private Customer customer;
    private List<Customer> customerList;
    private ErrorResponse errorResponse;

    public CustomerResponse(Customer customer) {
        this.customer = customer;
    }
    public CustomerResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
    public CustomerResponse(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
