package com.Inventory.System.notifications.TRA.Repository;

import com.Inventory.System.notifications.TRA.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {



}
