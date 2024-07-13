package com.Inventory.System.notifications.TRA.Controllers;



import com.Inventory.System.notifications.TRA.DTO.InventoryDTO;
import com.Inventory.System.notifications.TRA.Model.Inventory;
import com.Inventory.System.notifications.TRA.Services.EmailService;
import com.Inventory.System.notifications.TRA.Services.InventoryService;
import com.Inventory.System.notifications.TRA.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private EmailService mailingService;

    @Autowired
    private SlackService slackService;

    // Receive new stock
    @PostMapping("save")
    public Inventory receiveStock(@RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }

    // Write off inventory item
    @PutMapping("write")
    public String writeOff(@RequestParam Integer id) {
        return inventoryService.writeOff(id);
    }

    // Get all inventory items
    @GetMapping("getAll")
    public List<InventoryDTO> getInventory() {
        return inventoryService.getInventory();
    }

    // Get inventory by ID
    @GetMapping("getByInventoryId")
    public Inventory getInventoryById(@RequestParam Integer id) {
        return inventoryService.getInventoriesById(id);
    }

    // Get inventory by location
    @GetMapping("getByLocation")
    public List<Inventory> getInventoryByLocation(@RequestParam String location) {
        return inventoryService.getInventoriesByLocation(location);
    }

    // Get inventory by manager
    @GetMapping("getByManager")
    public List<Inventory> getInventoryByManager(@RequestParam String manager) {
        return inventoryService.getInventoriesByManager(manager);
    }

    // Get inventory by supplier
    @GetMapping("getBySupplier")
    public List<Inventory> getInventoryBySupplier(@RequestParam String supplier) {
        return inventoryService.getInventoriesBySupplier(supplier);
    }

    // Get inventory by phone number
    @GetMapping("getByPhoneNumber")
    public List<Inventory> getInventoryByPhoneNumber(@RequestParam String phoneNumber) {
        return inventoryService.getInventoriesByPhoneNumber(phoneNumber);
    }

    // Get inventory by opening hours
    @GetMapping("getByOpeningHours")
    public List<Inventory> getInventoryByOpeningHours(@RequestParam String openingHours) {
        return inventoryService.getInventoriesByOpeningHours(openingHours);
    }

    // Get inventory by closing hours
    @GetMapping("getByClosingHours")
    public List<Inventory> getInventoryByClosingHours(@RequestParam String closingHours) {
        return inventoryService.getInventoriesByClosingHours(closingHours);
    }

    // Send a message via Slack
    @GetMapping("messages")
    public void sendMessage() {
        slackService.sendMessage("", ""); // Empty parameters for demonstration
    }

    // Send a simple email
    @GetMapping("sendEmail")
    public String sendEmail() {
        String toEmail = "recipient@example.com"; // replace with recipient's email
        String fromEmail = "mohd.com25@gmail.com"; // replace with sender's email
        String emailBody = "Hello give be access to the database"; // replace with email body
        String subject = "Need DataBase access"; // replace with email subject
        return mailingService.sendSimpleMail(toEmail, fromEmail, emailBody, subject);
    }
}
