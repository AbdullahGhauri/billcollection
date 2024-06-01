package com.project.inventorydistribution.Services;

import com.project.inventorydistribution.DTOs.Customer;
import com.project.inventorydistribution.Models.ErrorResponse;
import com.project.inventorydistribution.Models.CustomerResponse;
import com.project.inventorydistribution.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    private String NO_RECORD_FOUND="No record found";
    private String CUSTOMER_NOT_FOUND = "Customer not found";
    private String CUSTOMER_BODY_EMPTY = "Customer body empty";



    public Customer getCustomerByIdFromRepo (long customerId){
        return customerRepository.findById(customerId).orElse(null);
    }

    public ResponseEntity<CustomerResponse> getAllCustomersService() {
        if(customerRepository.findAll().size()>0){
            return ResponseEntity.ok(new CustomerResponse(customerRepository.findAll()));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomerResponse(new ErrorResponse(NO_RECORD_FOUND)));
        }
    }

    public ResponseEntity<CustomerResponse> getCustomerByIdService(long customerId) {
        Customer customer = getCustomerByIdFromRepo(customerId);
        if(customer!=null){
            return ResponseEntity.ok(new CustomerResponse(customer));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomerResponse(new ErrorResponse(NO_RECORD_FOUND)));
        }
    }



    public ResponseEntity<CustomerResponse> addCustomerService(Customer customer) {
        try{
            return ResponseEntity.ok(new CustomerResponse(customerRepository.save(customer)));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomerResponse(new ErrorResponse(e.toString())));
        }
    }

    public ResponseEntity<CustomerResponse> updateCustomerService(long customerId, Customer customer) {
        Customer customerRepo = customerRepository.findById(customerId).orElse(null);
        if(customerRepo == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomerResponse(new ErrorResponse(CUSTOMER_NOT_FOUND)));
        }

        else{
            if(customer != null){
                customerRepo.setCustomerName(customer.getCustomerName());
                customerRepo.setCustomerCNIC(customer.getCustomerCNIC());
                customerRepo.setCustomerContactNo(customer.getCustomerContactNo());
                customerRepo.setCustomerMonthlyFee(customer.getCustomerMonthlyFee());
                customerRepo.setCustomerConnectionDate(customer.getCustomerConnectionDate());
                customerRepo.setCustomerDueDate(customer.getCustomerDueDate());
                customerRepo.setCustomerStatus(customer.isCustomerStatus());
                customerRepo.setCustomerAddress(customer.getCustomerAddress());

                return ResponseEntity.ok(new CustomerResponse(customerRepository.save(customerRepo)));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomerResponse(new ErrorResponse(CUSTOMER_BODY_EMPTY)));
            }
        }
    }
}
