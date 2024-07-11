package com.Inventory.System.notifications.TRA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Inventory.System.notifications.TRA.Model.Supplier;
import com.Inventory.System.notifications.TRA.Model.PaymentType;

import java.util.List;

// This is the Supplier Repository for connecting with MySQL
@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Integer> {

    // Fetches a Supplier by its ID.
    @Query("SELECT s FROM Supplier s WHERE s.id = :supplierId")
    Supplier getSupplierById(@Param("supplierId") Integer supplierId);

    // Fetches a list of Suppliers by their company name.
    @Query("SELECT s FROM Supplier s WHERE s.companyName = :companyName")
    List<Supplier> getSupplierByCompanyName(@Param("companyName") String companyName);

    // Fetches a list of Suppliers by their country.
    @Query("SELECT s FROM Supplier s WHERE s.country = :supplierCountry")
    List<Supplier> getSupplierByCountry(@Param("supplierCountry") String supplierCountry);

    // Fetches a list of Suppliers by their payment methods.
    @Query("SELECT s FROM Supplier s WHERE s.paymentMethods = :paymentMethods")
    List<Supplier> getSupplierByPaymentMethods(@Param("paymentMethods") PaymentType paymentMethods);

    // Fetches a list of Suppliers by their shipping methods.
    @Query("SELECT s FROM Supplier s WHERE s.shippingMethods = :shippingMethods")
    List<Supplier> getSupplierByShippingMethods(@Param("shippingMethods") String shippingMethods);

    // Fetches a list of Suppliers by their minimum order quantity.
    @Query("SELECT s FROM Supplier s WHERE s.minimumOrderQuantity = :minimumOrderQuantity")
    List<Supplier> getSupplierByMinimumOrderQuantity(@Param("minimumOrderQuantity") String minimumOrderQuantity);
}
