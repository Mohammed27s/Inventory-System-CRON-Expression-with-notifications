package com.Inventory.System.notifications.TRA.Services;


import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Models.Supplier;
import com.TRA.tra24Springboot.Repositories.ContactDetailsRepository;
import com.TRA.tra24Springboot.Repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ContactDetailsRepository contactDetailsRepository;

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
        contactDetails = contactDetailsRepository.save(contactDetails);
        supplier.setContactDetails(contactDetails);

        // Set other attributes for the supplier
        supplier.setCompanyName("Bahwan");
        supplier.setCountry("Oman");
        supplier.setNextDeliveryTime(new Date());
        supplier.setComplaints("no complaints");
        supplier.setPaymentMethods(PaymentType.BANK_TRANSFER);
        supplier.setShippingMethods("Air Freight");
        supplier.setMinimumOrderQuantity("100");
        supplier.setIsActive(Boolean.TRUE);

        // Save the supplier and return the saved instance
        return supplierRepository.save(supplier);
    }

    // Method to delete a supplier by ID
    public String deleteSupplier(Integer id) {
        try {
            Supplier supplier = supplierRepository.getById(id);
            supplier.setIsActive(Boolean.FALSE);
            supplierRepository.save(supplier);
            return "Success";
        } catch (Exception e) {
            return "Failed to delete supplier with ID " + id + ": " + e.getMessage();
        }
    }

    // Method to update a supplier by ID
    public String updateSupplier(Integer id) throws Exception {
        Supplier supplier = supplierRepository.getSupplierById(id);
        if (supplier == null) {
            throw new Exception("Supplier not found with ID: " + id);
        }

        // Update any necessary attributes of the supplier
        supplierRepository.save(supplier);
        return "Success";
    }

    // Method to retrieve all suppliers and convert them to DTOs
    public List<SupplierDTO> getSupplier(){
        List <Supplier> suppliers = supplierRepository.findAll();
        return SupplierDTO.convertToDTO(suppliers);
    }

    // Method to retrieve a supplier by ID
    public Supplier getSuppliersById(Integer id) throws Exception {
        Supplier supplier = supplierRepository.getSupplierById(id);
        if (supplier == null) {
            throw new Exception("Supplier not found with ID: " + id);
        }
        return supplier;
    }

    // Methods to retrieve suppliers by various criteria
    public List<Supplier> getSuppliersByCompanyName(String companyName) throws Exception {
        List<Supplier> suppliers = supplierRepository.getSupplierByCompanyName(companyName);
        if (suppliers.isEmpty()) {
            throw new Exception("No suppliers found with the company name: " + companyName);
        }
        return suppliers;
    }

    public List<Supplier> getSuppliersByCountry(String country) throws Exception {
        List<Supplier> suppliers = supplierRepository.getSupplierByCountry(country);
        if (suppliers.isEmpty()) {
            throw new Exception("No suppliers found with the country: " + country);
        }
        return suppliers;
    }

    public List<Supplier> getSuppliersByPaymentMethods(PaymentType paymentMethods) throws Exception {
        List<Supplier> suppliers = supplierRepository.getSupplierByPaymentMethods(paymentMethods);
        if (suppliers.isEmpty()) {
            throw new Exception("No suppliers found with the payment methods: " + paymentMethods);
        }
        return suppliers;
    }

    public List<Supplier> getSuppliersByShippingMethods(String shippingMethods) throws Exception {
        List<Supplier> suppliers = supplierRepository.getSupplierByShippingMethods(shippingMethods);
        if (suppliers.isEmpty()) {
            throw new Exception("No suppliers found with the shipping methods: " + shippingMethods);
        }
        return suppliers;
    }

    public List<Supplier> getSuppliersByMinimumOrderQuantity(String minimumOrderQuantity) throws Exception {
        List<Supplier> suppliers = supplierRepository.getSupplierByMinimumOrderQuantity(minimumOrderQuantity);
        if (suppliers.isEmpty()) {
            throw new Exception("No suppliers found with the minimum order quantity: " + minimumOrderQuantity);
        }
        return suppliers;
    }
}
