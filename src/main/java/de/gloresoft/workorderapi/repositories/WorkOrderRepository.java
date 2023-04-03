package de.gloresoft.workorderapi.repositories;

import de.gloresoft.workorderapi.entities.WorkOrder;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderRepository extends CrudRepository<WorkOrder, Long> {

	Iterable<WorkOrder> findByEmailId(String emailId);
}
