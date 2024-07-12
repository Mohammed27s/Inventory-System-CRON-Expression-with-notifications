package com.Inventory.System.notifications.TRA.Repository;

import com.Inventory.System.notifications.TRA.Model.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactDetailsRepo extends JpaRepository<ContactDetails, Integer> {


}
