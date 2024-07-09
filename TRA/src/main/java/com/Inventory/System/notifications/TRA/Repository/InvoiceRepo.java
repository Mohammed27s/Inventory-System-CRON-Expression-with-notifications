package com.Inventory.System.notifications.TRA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Inventory.System.notifications.TRA.Model.Invoice;
import com.Inventory.System.notifications.TRA.Model.Product;

import java.util.Date;
import java.util.List;


//This is Invoice Repository connect with MySQL
@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {


    @Query("SELECT i FROM Invoice i WHERE i.invoiceDate = :invoiceDate")
    List<Invoice> findByInvoiceDate(@Param("invoiceDate") Date invoiceDate);

    @Query("SELECT i FROM Invoice i WHERE i.dueDate < :dueDate")
    List<Invoice> findByDueDateBefore(@Param("dueDate") Date dueDate);

    @Query("SELECT i FROM Invoice i WHERE i.paidAmount > :amount")
    List<Invoice> findByPaidAmountGreaterThan(@Param("amount") double amount);

    @Query("SELECT i FROM Invoice i WHERE i.moneyCurrency = :moneyCurrency")
    List<Invoice> findByMoneyCurrency(@Param("moneyCurrency") String moneyCurrency);

    @Query("SELECT i FROM Invoice i JOIN FETCH i.products p WHERE p IN :products")
    List<Invoice> findByProducts(@Param("products") List<Product> products);


}
