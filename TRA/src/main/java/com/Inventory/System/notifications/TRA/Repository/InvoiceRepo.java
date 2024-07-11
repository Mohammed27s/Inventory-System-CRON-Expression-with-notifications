package com.Inventory.System.notifications.TRA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Inventory.System.notifications.TRA.Model.Invoice;
import com.Inventory.System.notifications.TRA.Model.Product;

import java.util.Date;
import java.util.List;

// This is the Invoice Repository for connecting with MySQL
@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {

    // Fetches an Invoice by its ID.
    @Query("SELECT in FROM Invoice in WHERE in.id = :inId")
    Invoice getInvoiceById(@Param("inId") Integer inId);

    // Fetches a list of Invoices by their creation date.
    @Query("SELECT v FROM Invoice v WHERE v.createdDate = :createdDate")
    List<Invoice> getInvoiceByCreatedDate(@Param("createdDate") Date createdDate);

    // Fetches a list of Invoices by their due date.
    @Query("SELECT v FROM Invoice v WHERE v.dueDate = :dueDate")
    List<Invoice> getInvoiceByDueDate(@Param("dueDate") Date dueDate);

    // Fetches a list of Invoices with due dates between the specified start and end dates.
    @Query("SELECT v FROM Invoice v WHERE v.dueDate BETWEEN :startDate AND :endDate")
    List<Invoice> getInvoicesByDueDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    // Fetches a list of overdue Invoices (invoices with due dates before the current date).
    @Query("SELECT v FROM Invoice v WHERE v.dueDate < :today")
    List<Invoice> getOverdueInvoices(@Param("today") Date today);

    // Fetches a list of Invoices created between the specified start and end dates.
    @Query("SELECT v FROM Invoice v WHERE v.createdDate BETWEEN :startDate AND :endDate")
    List<Invoice> getInvoicesCreatedBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    // Fetches a list of paid Invoices with payment dates between the specified start and end dates.
    @Query("SELECT v FROM Invoice v WHERE v.paymentDate BETWEEN :startDate AND :endDate")
    List<Invoice> getPaidInvoicesBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    // Fetches a list of Invoices by their invoice date.
    @Query("SELECT i FROM Invoice i WHERE i.invoiceDate = :invoiceDate")
    List<Invoice> findByInvoiceDate(@Param("invoiceDate") Date invoiceDate);

    // Fetches a list of Invoices with due dates before the specified date.
    @Query("SELECT i FROM Invoice i WHERE i.dueDate < :dueDate")
    List<Invoice> findByDueDateBefore(@Param("dueDate") Date dueDate);

    // Fetches a list of Invoices with paid amounts greater than the specified amount.
    @Query("SELECT i FROM Invoice i WHERE i.paidAmount > :amount")
    List<Invoice> findByPaidAmountGreaterThan(@Param("amount") double amount);

    // Fetches a list of Invoices by their money currency.
    @Query("SELECT i FROM Invoice i WHERE i.moneyCurrency = :moneyCurrency")
    List<Invoice> findByMoneyCurrency(@Param("moneyCurrency") String moneyCurrency);

    // Fetches a list of Invoices that contain specified products.
    @Query("SELECT i FROM Invoice i JOIN FETCH i.products p WHERE p IN :products")
    List<Invoice> findByProducts(@Param("products") List<Product> products);
}
