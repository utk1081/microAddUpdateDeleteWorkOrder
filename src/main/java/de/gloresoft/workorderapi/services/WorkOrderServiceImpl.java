package de.gloresoft.workorderapi.services;

import de.gloresoft.workorderapi.entities.WorkOrder;
import de.gloresoft.workorderapi.exceptions.InsufficientBillaleDaysException;
import de.gloresoft.workorderapi.exceptions.ResourceAlreadyExistsException;
import de.gloresoft.workorderapi.exceptions.ResourceNotFoundException;
import de.gloresoft.workorderapi.repositories.WorkOrderRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {

    private final WorkOrderRepository workOrderRepository;

    public WorkOrderServiceImpl(WorkOrderRepository workOrderRepository) {
        this.workOrderRepository = workOrderRepository;
    }

    @Override
    public Iterable<WorkOrder> findAllOrders() {
        return this.workOrderRepository.findAll();
    }

    @Override
    public WorkOrder findById(Long id) {
        return this.workOrderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("This id:"+id+" does not exist."));
    }

    @Override
    public Iterable<WorkOrder> findByEmailId(String emailId) {
        return this.workOrderRepository.findByEmailId(emailId);
    }
    @Override
    public void removeById(Long id) {
        if(!this.workOrderRepository.existsById(id)) {
            throw new ResourceNotFoundException("This id:"+id+" does not exist.");
        }
        this.workOrderRepository.deleteById(id);
    }

    @Override
    public void addWorkOrder(WorkOrder workOrder) {
        if(this.workOrderRepository.existsById(workOrder.getId())) {
            throw new ResourceAlreadyExistsException("This id:"+workOrder.getId()+" already exists.");
        }
        workOrder.setDateFrom(null);
        workOrder.setDateTo(null);
        this.workOrderRepository.save(workOrder);
    }

    @Override
    public void updateWorkOrder(WorkOrder workOrder, Long id) {

        if(!this.workOrderRepository.existsById(id)) {
            throw new ResourceNotFoundException("This id:"+id+" does not exist.");
        }
        workOrder.setId(id);
        this.workOrderRepository.save(workOrder);
        /*
    	// step 1 fetch all record against an email id
    	List<WorkOrder> listOfWorkOrder=(List<WorkOrder>) this.findByEmailId(workOrder.getEmailId());
    	int billing_remaining_days=workOrder.getWorkingDays().intValue();
    	int isBilling_Possible=0;
    	// step 2 check if sufficient workorder available for billing against an email id
    	for (WorkOrder workOrderDatabase : listOfWorkOrder) {
    		isBilling_Possible=isBilling_Possible+workOrderDatabase.getWorkingDays().intValue();
    		}
    	if(isBilling_Possible>=billing_remaining_days) {
    		for (WorkOrder workOrderDatabase : listOfWorkOrder) {
                     if(billing_remaining_days>=0) {
    				   if(billing_remaining_days-workOrderDatabase.getWorkingDays().intValue() >=0) {
    				    					billing_remaining_days=billing_remaining_days-workOrderDatabase.getWorkingDays().intValue();
    					workOrderDatabase.setWorkingDays(0);
    			   	}
    				   else {
    					workOrderDatabase.setWorkingDays(workOrderDatabase.getWorkingDays().intValue()-billing_remaining_days);
    					billing_remaining_days=0;

    				}
    				 this.workOrderRepository.save(workOrderDatabase);
    			}
    		}
    	}
    	else {
		      throw new InsufficientBillaleDaysException("This id:"+workOrder.getId()+" insufficient billable days.");

    	}
    	*/
    
    }

}
