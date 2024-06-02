package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c FROM Customer c WHERE c.customerId NOT IN " +
            "(SELECT b.customer.customerId FROM BillCollection b WHERE FUNCTION('MONTH', b.billInvoiceDate) = :month " +
            "AND FUNCTION('YEAR', b.billInvoiceDate) = :year)")
    List<Customer> findCustomersWithPendingBills(@Param("month") int month, @Param("year") int year);

    @Query("SELECT c FROM Customer c WHERE c.customerId IN " +
            "(SELECT b.customer.customerId FROM BillCollection b WHERE FUNCTION('MONTH', b.billInvoiceDate) = :month " +
            "AND FUNCTION('YEAR', b.billInvoiceDate) = :year)")
    List<Customer> findCustomersWithRecivedBills(@Param("month") int month, @Param("year") int year);


    Optional<Customer> findByCustomerCNIC(String cnic);


}
