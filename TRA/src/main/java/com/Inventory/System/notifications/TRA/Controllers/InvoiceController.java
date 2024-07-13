package com.Inventory.System.notifications.TRA.Controllers;

import com.Inventory.System.notifications.TRA.Model.Invoice;
import com.Inventory.System.notifications.TRA.Repository.InvoiceRepo;
import com.Inventory.System.notifications.TRA.Services.InvoiceService;
import com.Inventory.System.notifications.TRA.Services.SlackService;
import com.Inventory.System.notifications.TRA.Util.DateHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    SlackService slackService;

    // Endpoint to create an invoice
    @PostMapping("createInvoice")
    public Invoice createInvoice(Invoice invoice) throws Exception {
        slackService.sendMessage("mohammed", "Testing Slack");
        return invoiceService.createInvoice(invoice);
    }

    // Scheduled task to send due date reminders
    @Scheduled(cron = "0 0 9 * * ?") // Runs every day at 9:00 AM
    @PostMapping("dueDate")
    public void sendDueDateReminder() {
        Integer remainingDays = 1;
        List<Invoice> invoices = invoiceService.getInvoiceDueInNextDays(remainingDays);
        for (Invoice invoice : invoices) {
            StringBuilder message = new StringBuilder();
            message.append("Reminder: Invoice #")
                    .append(invoice.getId())
                    .append(" is due on ")
                    .append(invoice.getDueDate().toString());
            slackService.sendMessage("api testing", message.toString());
        }
    }

    // Scheduled task to generate a weekly invoice report
    @Scheduled(cron = "0 0 9 * * 0") // Runs every Sunday at 9:00 AM
    @PostMapping("The week Report list")
    public void weeklyInvoiceReport() {
        Date today = new Date();
        Date startDate = DateHelperUtils.subtractDays(today, 6); // Previous 7 days

        List<Invoice> createdInvoices = invoiceService.getInvoicesCreatedBetween(startDate, today);
        List<Invoice> paidInvoices = invoiceService.getPaidInvoicesBetween(startDate, today);
        List<Invoice> overdueInvoices = invoiceService.getOverDueInvoices();

        StringBuilder report = new StringBuilder();
        report.append("The Summary Report:\n")
                .append("Invoices Created:\n");
        appendInvoicesToReport(report, createdInvoices);
        report.append("\nInvoices Paid:\n");
        appendInvoicesToReport(report, paidInvoices);
        report.append("\nOverdue Invoices:\n");
        appendInvoicesToReport(report, overdueInvoices);

        slackService.sendMessage("taiba", report.toString());
    }

    // Helper method to append invoice details to a report
    private void appendInvoicesToReport(StringBuilder report, List<Invoice> invoices) {
        for (Invoice invoice : invoices) {
            report.append("Invoice #")
                    .append(invoice.getId())
                    .append(" - Due on ")
                    .append(invoice.getDueDate().toString())
                    .append("\n");
        }
    }

    // Scheduled task to generate a monthly invoice report
    @Scheduled(cron = "0 0 9 1 * ?") // Runs on the first day of every month at 9:00 AM
    @PostMapping("The month Report")
    public void monthlyInvoiceReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1); // First day of the current month
        Date startDate = calendar.getTime();

        calendar.add(Calendar.MONTH, 1); // Move to the next month
        calendar.add(Calendar.DAY_OF_MONTH, -1); // Last day of the current month
        Date endDate = calendar.getTime();

        List<Invoice> createdInvoices = invoiceService.getInvoicesCreatedBetween(startDate, endDate);
        List<Invoice> paidInvoices = invoiceService.getPaidInvoicesBetween(startDate, endDate);
        List<Invoice> overdueInvoices = invoiceService.getOverDueInvoices();

        StringBuilder report = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
        report.append("The Month Invoice Report - ")
                .append(dateFormat.format(startDate))
                .append(":\n")
                .append("Invoices Created:\n");
        appendInvoicesToReport(report, createdInvoices);
        report.append("\nInvoices Paid:\n");
        appendInvoicesToReport(report, paidInvoices);
        report.append("\nOverdue Invoices:\n");
        appendInvoicesToReport(report, overdueInvoices);

        slackService.sendMessage("api test", report.toString());

    }

    }