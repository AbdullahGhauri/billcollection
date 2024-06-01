package com.project.inventorydistribution.Services;

import com.project.inventorydistribution.DTOs.BillCollection;
import com.project.inventorydistribution.DTOs.Customer;
import com.project.inventorydistribution.Models.BillCollectionResponse;
import com.project.inventorydistribution.Models.CustomerResponse;
import com.project.inventorydistribution.Models.ErrorResponse;
import com.project.inventorydistribution.Repositories.BillCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BillCollectionService {

    @Autowired
    BillCollectionRepository billCollectionRepository;
    
    private String NO_RECORD_FOUND="No record found";
    private String BILL_NOT_FOUND = "Bill not found";
    private String Bill_BODY_EMPTY = "Bill body empty";

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
}
