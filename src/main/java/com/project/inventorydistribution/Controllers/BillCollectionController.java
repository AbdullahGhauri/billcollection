package com.project.inventorydistribution.Controllers;

import com.project.inventorydistribution.DTOs.Agent;
import com.project.inventorydistribution.DTOs.BillCollection;
import com.project.inventorydistribution.DTOs.Customer;
import com.project.inventorydistribution.Models.AgentResponse;
import com.project.inventorydistribution.Models.BillCollectionResponse;
import com.project.inventorydistribution.Models.CustomerResponse;
import com.project.inventorydistribution.Services.AgentService;
import com.project.inventorydistribution.Services.BillCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billCollection")
@CrossOrigin(origins = "*")
public class BillCollectionController {
    @Autowired
    BillCollectionService billCollectionService;

    @GetMapping("/getAllBillCollection")
    public ResponseEntity<BillCollectionResponse> getAllBillCollection(){
        return billCollectionService.getAllBillCollectionsService();
    }

    @GetMapping("/getBillCollectionById/{billCollectionId}")
    public ResponseEntity<BillCollectionResponse> getBillCollectionById(@PathVariable long billCollectionId){
        return billCollectionService.getBillCollectionByIdService(billCollectionId);
    }
    @PostMapping(value = "/addBillCollection")
    public ResponseEntity<BillCollectionResponse> addBillCollection(@RequestBody BillCollection billCollection){
        return billCollectionService.addBillCollectionService(billCollection);
    }
    @PutMapping("/updateBillCollection/{billCollectionId}")
    public ResponseEntity<BillCollectionResponse> updateBillCollection(@PathVariable long billCollectionId, @RequestBody BillCollection billCollection){
        return billCollectionService.updateBillCollectionService(billCollectionId,billCollection);
    }
}
