package com.Inventory.System.notifications.TRA.Model;

import jakarta.persistence.Entity;
import lombok.Data;

//This is class for making report for
@Entity
@Data
public class Report extends BaseEntity{

    String ReportTitle;
    ReportType reportType; //This is an enum for business performance
    ReportStatus reportStatus; //This is an enum
    String content; //This is for saving HTMl page as JSON

}
