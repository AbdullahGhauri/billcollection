package com.project.inventorydistribution.Controllers;

import com.project.inventorydistribution.DTOs.Customer;
import com.project.inventorydistribution.Models.CustomerResponse;
import com.project.inventorydistribution.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/getAllCustomers")
    public ResponseEntity<CustomerResponse> getAllCustomers(){
        return customerService.getAllCustomersService();
    }

    @GetMapping("/getCustomerById/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable long customerId){
        return customerService.getCustomerByIdService(customerId);
    }
    @PostMapping(value = "/addCustomer")
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody Customer customer){
        return customerService.addCustomerService(customer);
    }
    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable long customerId, @RequestBody Customer customer){
        return customerService.updateCustomerService(customerId,customer);
    }
}
