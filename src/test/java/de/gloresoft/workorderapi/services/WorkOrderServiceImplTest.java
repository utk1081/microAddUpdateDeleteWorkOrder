package de.gloresoft.workorderapi.services;

import de.gloresoft.workorderapi.entities.WorkOrder;
import de.gloresoft.workorderapi.exceptions.ResourceAlreadyExistsException;
import de.gloresoft.workorderapi.exceptions.ResourceNotFoundException;
import de.gloresoft.workorderapi.repositories.WorkOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Test Class for WorkOrderServiceImpl")
@ExtendWith(MockitoExtension.class)
class WorkOrderServiceImplTest {

    WorkOrderService workOrderService;

    @Mock
    WorkOrderRepository workOrderRepository;

    @BeforeEach
    void setUp() {
        workOrderService = new WorkOrderServiceImpl(workOrderRepository);
    }

    @Test
    void findAllOrders() {
        List<WorkOrder> workOrders = new ArrayList<>();
        workOrders.add(new WorkOrder(1L, "GG", "123", "abc@workmail.com", LocalDate.of(2022, 7, 8), LocalDate.of(2022, 10, 8), 50,0));
        when(workOrderRepository.findAll()).thenReturn(workOrders);

        Iterable<WorkOrder> allOrders = workOrderService.findAllOrders();
        assertEquals(1, ((List)allOrders).size());
    }

    @Test
    void findById() {
        WorkOrder workOrder = new WorkOrder(1L, "GG", "123", "abc@workmail.com", LocalDate.of(2022, 7, 8), LocalDate.of(2022, 10, 8), 50,0);
        when(workOrderRepository.findById(1L)).thenReturn(Optional.of(workOrder));
        WorkOrder workOrder1 = workOrderService.findById(1L);
        assertEquals("123", workOrder1.getProjectNumber());
    }

    @Test
    void removeById() {
        when(workOrderRepository.existsById(2L)).thenReturn(true);
        doNothing().when(workOrderRepository).deleteById(2L);
        workOrderService.removeById(2L);
        verify(workOrderRepository, times(1)).existsById(anyLong());
    }

    @Test
    void removeByIdException() {
        when(workOrderRepository.existsById(2L)).thenReturn(false);
        Exception e = assertThrows(ResourceNotFoundException.class, ()-> workOrderService.removeById(2L));
        assertEquals("This id:2 does not exist.", e.getMessage());
    }

    @Test
    void addWorkOrder() {
        WorkOrder workOrder = new WorkOrder(1L, "GG", "123", "abc@workmail.com", LocalDate.of(2022, 7, 8), LocalDate.of(2022, 10, 8), 50,0);
        when(workOrderRepository.existsById(workOrder.getId())).thenReturn(false);
        when(workOrderRepository.save(any())).thenReturn(new WorkOrder());
        workOrderService.addWorkOrder(workOrder);
        verify(workOrderRepository, times(1)).existsById(anyLong());
    }

    @Test
    void addWorkOrderException() {
        WorkOrder workOrder = new WorkOrder();
        workOrder.setId(2L);
        when(workOrderRepository.existsById(2L)).thenReturn(true);
        Exception e = assertThrows(ResourceAlreadyExistsException.class, ()-> workOrderService.addWorkOrder(workOrder));
        assertEquals("This id:2 already exists.", e.getMessage());
    }

    @Test
    void updateWorkOrder() {
        WorkOrder workOrder = new WorkOrder(1L, "GG", "123", "abc@workmail.com", LocalDate.of(2022, 7, 8), LocalDate.of(2022, 10, 8), 50,0);
        when(workOrderRepository.existsById(workOrder.getId())).thenReturn(true);
        when(workOrderRepository.save(any())).thenReturn(new WorkOrder());
        workOrderService.updateWorkOrder(workOrder, 1L);
        verify(workOrderRepository, times(1)).existsById(anyLong());
    }

    @Test
    void updateWorkOrderException() {
        WorkOrder workOrder = new WorkOrder();
        workOrder.setId(2L);
        when(workOrderRepository.existsById(2L)).thenReturn(false);
        Exception e = assertThrows(ResourceNotFoundException.class, ()-> workOrderService.updateWorkOrder(workOrder, 2L));
        assertEquals("This id:2 does not exist.", e.getMessage());
    }
}