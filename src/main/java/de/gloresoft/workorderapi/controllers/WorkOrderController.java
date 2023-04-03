package de.gloresoft.workorderapi.controllers;

import de.gloresoft.workorderapi.entities.History;
import de.gloresoft.workorderapi.entities.WorkOrder;
import de.gloresoft.workorderapi.exceptions.ResourceNotFoundException;
import de.gloresoft.workorderapi.services.HistoryService;
import de.gloresoft.workorderapi.services.WorkOrderService;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "${workorders.gui.application.url}")
public class WorkOrderController {

    private final WorkOrderService workOrderService;
   @Autowired
    HistoryService historyService;

    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    @PostMapping("/workOrders")
    public void addWorkOrder(@RequestBody WorkOrder workOrder) {
        this.workOrderService.addWorkOrder(workOrder);
        // Add record to History table 
        History history=new History();
        history.setId(0l);
        history.setEmailId(workOrder.getEmailId());
        history.setProjectNumber(workOrder.getProjectNumber());
        history.setUserName(workOrder.getEmployeeName());
        long time = System.currentTimeMillis();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
        history.setTimestamp(timestamp);
        history.setAction("New work order entered by "+workOrder.getEmployeeName()+" No of days "+workOrder.getWorkingDays());
      //  historyService.addHistory(history);
        // Rest template call strats to update history table 16-03-2023
        //end point of history svc in aws is 192.168.4.156:8080
      //  final String uri = "http://192.168.4.156:8080/histories/history";
        final String uri = "http://afaa98937d5fa459f8576b2ab69770eb-1295660024.eu-central-1.elb.amazonaws.com:8083/histories";
        RestTemplate restTemplate = new RestTemplate();
        //String result = restTemplate.getForObject(uri, String.class);
        ResponseEntity<History> result = restTemplate.postForEntity(uri, history, History.class);
        
       // System.out.println("print after RestTemplate............................##########");
        //end restcall
       
    }
    @DeleteMapping("/workOrders/{id}")
    public void deleteWorkOrder(@PathVariable String id) throws ResourceNotFoundException {
        long longId;
        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException(nfe.getMessage());
        }
        this.workOrderService.removeById(longId);
    }

    @PutMapping("/workOrders/{id}")
    public void updateWorkOrder(@RequestBody WorkOrder workOrder, @PathVariable String id) throws ResourceNotFoundException {
        long longId;
        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException(nfe.getMessage());
        }
        this.workOrderService.updateWorkOrder(workOrder, longId);
        // Add record to History table 
        History history=new History();
        history.setId(0l);
        history.setEmailId(workOrder.getEmailId());
        history.setProjectNumber(workOrder.getProjectNumber());
        history.setUserName(workOrder.getEmployeeName());
        long time = System.currentTimeMillis();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
        history.setTimestamp(timestamp);
        history.setAction("Timesheet Updated by "+workOrder.getEmployeeName()+" No of days "+workOrder.getBillingDays());
        historyService.addHistory(history);
    }
}
