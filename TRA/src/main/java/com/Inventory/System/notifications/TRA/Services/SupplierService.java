package com.Inventory.System.notifications.TRA.Services;


import com.Inventory.System.notifications.TRA.DTO.SupplierDTO;
import com.Inventory.System.notifications.TRA.Model.ContactDetails;
import com.Inventory.System.notifications.TRA.Model.PaymentType;
import com.Inventory.System.notifications.TRA.Model.Supplier;
import com.Inventory.System.notifications.TRA.Repository.ContactDetailsRepo;
import com.Inventory.System.notifications.TRA.Repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    ContactDetailsRepo contactDetailsRepo;

    // Method to add a new supplier
    public Supplier addSupplier(Supplier supplier) {
        // Create new contact details for the supplier
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setAddress("Muscat");
        contactDetails.setEmail("bahwan@gmail.com");
        contactDetails.setPhoneNumber("99999999");
        contactDetails.setFaxNumber("22222");
        contactDetails.setPostalCode("11111");

        // Save contact details and set them in the supplier
        contactDetails = contactDetailsRepo.save(contactDetails);
        supplier.setContactDetails(contactDetails);

        // Set other attributes for the supplier
        supplier.setSupplierName("Bahwan");
        supplier.setCountry("Oman");
        supplier.setNextDeliveryTime(new Date());
        supplier.setComplaints("no complaints");
        supplier.setPaymentMethods(PaymentType.BANK_TRANSFER);
        supplier.setShippingMethods("Air Freight");
        supplier.setMinimumOrderQuantity("100");
        supplier.setIsActive(Boolean.TRUE);

        // Save the supplier and return the saved instance
        return supplierRepo.save(supplier);
    }

    // Method to delete a supplier by ID
    public String deleteSupplier(Integer id) {
        try {
            Supplier supplier = supplierRepo.getById(id);
            supplier.setIsActive(Boolean.FALSE);
            supplierRepo.save(supplier);
            return "Success";
        } catch (Exception e) {
            return "Failed to delete supplier with ID " + id + ": " + e.getMessage();
        }
    }

    // Method to update a supplier by ID
    public String updateSupplier(Integer id) throws Exception {
        Supplier supplier = supplierRepo.getSupplierById(id);
        if (supplier == null) {
            throw new Exception("Supplier not found with ID: " + id);
        }

        // Update any necessary attributes of the supplier
        supplierRepo.save(supplier);
        return "Success";
    }

    // Method to retrieve all suppliers and convert them to DTOs
    public List<SupplierDTO> getSupplier(){
        List <Supplier> suppliers = supplierRepo.findAll();
        return SupplierDTO.convertToDTO(suppliers);
    }

    // Method to retrieve a supplier by ID
    public Supplier getSuppliersById(Integer id) throws Exception {
        Supplier supplier = supplierRepo.getSupplierById(id);
        if (supplier == null) {
            throw new Exception("Supplier not found with ID: " + id);
        }
        return supplier;
    }

    // Methods to retrieve suppliers by various criteria
    public List<Supplier> getSuppliersByCompanyName(String companyName) throws Exception {
        List<Supplier> suppliers = supplierRepo.getSupplierByCompanyName(companyName);
        if (suppliers.isEmpty()) {
            throw new Exception("No suppliers found with the company name: " + companyName);
        }
        return suppliers;
    }

    public List<Supplier> getSuppliersByCountry(String country) throws Exception {
        List<Supplier> suppliers = supplierRepo.getSupplierByCountry(country);
        if (suppliers.isEmpty()) {
            throw new Exception("No suppliers found with the country: " + country);
        }
        return suppliers;
    }

    public List<Supplier> getSuppliersByPaymentMethods(PaymentType paymentMethods) throws Exception {
        List<Supplier> suppliers = supplierRepo.getSupplierByPaymentMethods(paymentMethods);
        if (suppliers.isEmpty()) {
            throw new Exception("No suppliers found with the payment methods: " + paymentMethods);
        }
        return suppliers;
    }

    public List<Supplier> getSuppliersByShippingMethods(String shippingMethods) throws Exception {
        List<Supplier> suppliers = supplierRepo.getSupplierByShippingMethods(shippingMethods);
        if (suppliers.isEmpty()) {
            throw new Exception("No suppliers found with the shipping methods: " + shippingMethods);
        }
        return suppliers;
    }

    public List<Supplier> getSuppliersByMinimumOrderQuantity(String minimumOrderQuantity) throws Exception {
        List<Supplier> suppliers = supplierRepo.getSupplierByMinimumOrderQuantity(minimumOrderQuantity);
        if (suppliers.isEmpty()) {
            throw new Exception("No suppliers found with the minimum order quantity: " + minimumOrderQuantity);
        }
        return suppliers;
    }
}
