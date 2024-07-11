package com.Inventory.System.notifications.TRA.Services;

import com.Inventory.System.notifications.TRA.Model.Invoice;
import com.Inventory.System.notifications.TRA.Model.Product;
import com.Inventory.System.notifications.TRA.Repository.InvoiceRepo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepo invoiceRepo;

    @Autowired
    private ProductService productService;
    private DateUtils DateHelperUtils;

    // Method to create a new invoice
    public Invoice createInvoice(Invoice invoice) throws Exception {
        // Create a new product (example)
        Product product = new Product();
        // Add product to invoice (example)
        Product products = productService.addProduct(product);

        invoice.setProducts(Arrays.asList(products));
        invoice.setCreatedDate(new Date());
        invoice.setTotalAmount(Double.parseDouble("36"));
        invoice.setIsActive(Boolean.TRUE);
        invoice.setPaidAmount(Double.parseDouble("89"));
        Date dueDate = DateHelperUtils.addDays(invoice.getCreatedDate(), 1);

        // Save and return the created invoice
        return invoiceRepo.save(invoice);
    }

    // Method to fetch an invoice by ID
    public Invoice getBInvoiceById(Integer id) {
        return invoiceRepo.getInvoiceById(id);
    }

    // Method to fetch invoices due on a specific date
    public List<Invoice> getInvoiceByDueDate(Date dueDate) {
        return invoiceRepo.getInvoiceByDueDate(dueDate);
    }

    // Method to fetch invoices due in the next few days
    public List<Invoice> getInvoiceDueInNextDays(Integer days) {
        Date today = new Date();
        Date dueDate = DateHelperUtils.addDays(today, days);
        return invoiceRepo.getInvoicesByDueDateBetween(today, dueDate);
    }

    // Method to fetch invoices created between two dates
    public List<Invoice> getInvoicesCreatedBetween(Date startDate, Date endDate) {
        return invoiceRepo.getInvoicesCreatedBetween(startDate, endDate);
    }

    // Method to fetch paid invoices between two dates
    public List<Invoice> getPaidInvoicesBetween(Date startDate, Date endDate) {
        return invoiceRepo.getPaidInvoicesBetween(startDate, endDate);
    }

    // Method to fetch overdue invoices
    public List<Invoice> getOverDueInvoices() {
        Date today = new Date();
        return invoiceRepo.getOverdueInvoices(today);
    }
}
