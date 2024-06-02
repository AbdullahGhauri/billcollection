package com.project.inventorydistribution.Services;

import com.project.inventorydistribution.DTOs.Agent;
import com.project.inventorydistribution.DTOs.BillCollection;
import com.project.inventorydistribution.DTOs.Customer;
import com.project.inventorydistribution.Models.*;
import com.project.inventorydistribution.Repositories.AgentRepository;
import com.project.inventorydistribution.Repositories.BillCollectionRepository;
import com.project.inventorydistribution.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BillCollectionService {

    @Autowired
    BillCollectionRepository billCollectionRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AgentRepository agentRepository;
    
    private String NO_RECORD_FOUND="No record found";
    private String BILL_NOT_FOUND = "Bill not found";
    private String Bill_BODY_EMPTY = "Bill body empty";
    private String AGENT_NOT_FOUND = "Agent not found";
    private String CUSTOMER_NOT_FOUND =  "Customer not found";

    public BillCollection getBillCollectionByIdFromRepo (long billCollectionId){
        return billCollectionRepository.findById(billCollectionId).orElse(null);
    }

    public ResponseEntity<BillCollectionResponse> getAllBillCollectionsService() {
        if(billCollectionRepository.findAll().size()>0){
            return ResponseEntity.ok(new BillCollectionResponse(billCollectionRepository.findAll()));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BillCollectionResponse(new ErrorResponse(NO_RECORD_FOUND)));
        }
    }

    public ResponseEntity<BillCollectionResponse> getBillCollectionByIdService(long billCollectionId) {
        BillCollection billCollection = getBillCollectionByIdFromRepo(billCollectionId);
        if(billCollection!=null){
            return ResponseEntity.ok(new BillCollectionResponse(billCollection));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BillCollectionResponse(new ErrorResponse(NO_RECORD_FOUND)));
        }
    }

    public ResponseEntity<BillCollectionResponse> addBillCollectionService(BillCollection billCollection) {
        try{
            String cnic = billCollection.getCustomer().getCustomerCNIC();
            String agentUserId = billCollection.getAgent().getAgentUserId();

            var agent = agentRepository.findByAgentUserId(agentUserId).orElse(null);
            if(agent == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BillCollectionResponse(new ErrorResponse(AGENT_NOT_FOUND)));
            }

            var customer = customerRepository.findByCustomerCNIC(cnic).orElse(null);
            if(customer == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BillCollectionResponse(new ErrorResponse(CUSTOMER_NOT_FOUND)));
            }
            billCollection.setCustomer(customer);
            billCollection.setAgent(agent);

            return ResponseEntity.ok(new BillCollectionResponse(billCollectionRepository.save(billCollection)));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BillCollectionResponse(new ErrorResponse(e.toString())));
        }
    }

    public ResponseEntity<BillCollectionResponse> updateBillCollectionService(long billCollectionId, BillCollection billCollection) {
        {
            BillCollection billCollectionRepo = billCollectionRepository.findById(billCollectionId).orElse(null);
            if(billCollectionRepo == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BillCollectionResponse(new ErrorResponse(BILL_NOT_FOUND)));
            }

            else{
                if(billCollection != null){
                    billCollectionRepo.setBillCollectionId(billCollection.getBillCollectionId());
                    billCollectionRepo.setBillStatus(billCollection.isBillStatus());
                    billCollectionRepo.setAgent(billCollection.getAgent());
                    billCollectionRepo.setBillInvoiceDate(billCollection.getBillInvoiceDate());
                    billCollectionRepo.setAmountReceived(billCollection.getAmountReceived());
                    billCollectionRepo.setCustomer(billCollection.getCustomer());

                    return ResponseEntity.ok(new BillCollectionResponse(billCollectionRepository.save(billCollectionRepo)));
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BillCollectionResponse(new ErrorResponse(Bill_BODY_EMPTY)));
                }
            }
        }
    }

    public ResponseEntity<CustomerResponse> getPendingOrReceivedCustomerService(CustomerBillStatusRequest sustomerBillStatusRequest) {

        LocalDate billDate = sustomerBillStatusRequest.getBillDate();
        boolean isPending = sustomerBillStatusRequest.isPending();
        if(isPending){
            List<Customer> pendingBillCustomers = customerRepository.findCustomersWithPendingBills(billDate.getMonth().getValue(),billDate.getYear());
            if(pendingBillCustomers.size()==0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomerResponse(new ErrorResponse(NO_RECORD_FOUND)));
            }
            return ResponseEntity.ok(new CustomerResponse(pendingBillCustomers));

        }else{
            List<Customer> receivedBillCustomers = customerRepository.findCustomersWithRecivedBills(billDate.getMonth().getValue(),billDate.getYear());
            if(receivedBillCustomers.size()==0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomerResponse(new ErrorResponse(NO_RECORD_FOUND)));
            }
            return ResponseEntity.ok(new CustomerResponse(receivedBillCustomers));
        }

    }
}
