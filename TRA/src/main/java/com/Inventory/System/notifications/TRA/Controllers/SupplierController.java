package com.Inventory.System.notifications.TRA.Controllers;


import com.Inventory.System.notifications.TRA.DTO.SupplierDTO;
import com.Inventory.System.notifications.TRA.Model.PaymentType;
import com.Inventory.System.notifications.TRA.Model.Supplier;
import com.Inventory.System.notifications.TRA.Repository.SupplierRepo;
import com.Inventory.System.notifications.TRA.Services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    SupplierService supplierService;

    // Endpoint to add a new supplier
    @PostMapping("add")
    public Supplier addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    // Endpoint to update an existing supplier
    @PutMapping("update")
    public <T> ResponseEntity<T> updateSupplier(@RequestParam Integer id) {
        try {
            String result = supplierService.updateSupplier(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Updating supplier failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to delete a supplier by ID
    @PostMapping("remove")
    public <T> ResponseEntity<T> delete(@RequestParam Integer id) {
        try {
            String result = supplierService.deleteSupplier(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Deleting supplier failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get all suppliers
    @GetMapping("allData")
    public List<SupplierDTO> getSupplier() {
        return supplierService.getSupplier();
    }

    // Endpoint to get a supplier by ID
    @GetMapping("getSupplierById")
    public <T> ResponseEntity<T> getSupplierById(@RequestParam Integer id) {
        try {
            Supplier supplier = supplierService.getSuppliersById(id);
            return (ResponseEntity<T>) new ResponseEntity<>(supplier, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving supplier failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get suppliers by company name
    @GetMapping("getByCompanyName")
    public <T> ResponseEntity<T> getSupplierByCompanyName(@RequestParam String companyName) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByCompanyName(companyName);
            return (ResponseEntity<T>) new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving suppliers by company name failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get suppliers by country
    @GetMapping("getByCountry")
    public <T> ResponseEntity<T> getSupplierByCountry(@RequestParam String country) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByCountry(country);
            return (ResponseEntity<T>) new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving suppliers by country failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get suppliers by payment methods
    @GetMapping("getByPaymentMethods")
    public <T> ResponseEntity<T> getSupplierByPaymentMethods(@RequestParam PaymentType paymentMethods) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByPaymentMethods(paymentMethods);
            return (ResponseEntity<T>) new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving suppliers by payment methods failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get suppliers by shipping methods
    @GetMapping("getByShipping")
    public <T> ResponseEntity<T> getSupplierByShippingMethods(@RequestParam String shippingMethods) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByShippingMethods(shippingMethods);
            return (ResponseEntity<T>) new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving suppliers by shipping methods failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get suppliers by minimum order quantity
    @GetMapping("geQuantity")
    public <T> ResponseEntity<T> getSupplierByMinimumOrderQuantity(@RequestParam String minimumOrderQuantity) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByMinimumOrderQuantity(minimumOrderQuantity);
            return (ResponseEntity<T>) new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving suppliers by minimum order quantity failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
