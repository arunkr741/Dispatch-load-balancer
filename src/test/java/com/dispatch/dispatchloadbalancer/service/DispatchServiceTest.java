package com.dispatch.dispatchloadbalancer.service;

import com.dispatch.dispatchloadbalancer.model.Order;
import com.dispatch.dispatchloadbalancer.model.Priority;
import com.dispatch.dispatchloadbalancer.model.Vehicle;
import com.dispatch.dispatchloadbalancer.repository.OrderRepository;
import com.dispatch.dispatchloadbalancer.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DispatchServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private DispatchService dispatchService;

    @Test
    public void generateDispatchPlan_ShouldReturnValidPlan() {
        // Arrange
        List<Order> orders = new ArrayList<>(Arrays.asList(
                new Order("ORD001", 12.2958, 76.6394, "MG Road, Mysore", 10.0, Priority.HIGH)
        ));

        List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(
                new Vehicle("VEH001", 100.0, 12.3180, 76.6548, "Vijayanagar, Mysore")
        ));

        when(orderRepository.findAll()).thenReturn(orders);
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        // Act
        Map<String, Object> result = dispatchService.generateDispatchPlan();

        // Assert
        assertNotNull(result);
        assertTrue(result.containsKey("dispatchPlan"));
        verify(orderRepository, times(1)).findAll();
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    public void generateDispatchPlan_ShouldAssignHighPriorityOrdersFirst() {
        // Arrange
        List<Order> orders = new ArrayList<>(Arrays.asList(
                new Order("ORD001", 12.2958, 76.6394, "MG Road, Mysore", 10.0, Priority.MEDIUM),
                new Order("ORD002", 18.5204, 73.8567, "Baner, Pune", 20.0, Priority.HIGH)
        ));

        List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(
                new Vehicle("VEH001", 100.0, 12.3180, 76.6548, "Vijayanagar, Mysore")
        ));

        when(orderRepository.findAll()).thenReturn(orders);
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        // Act
        Map<String, Object> result = dispatchService.generateDispatchPlan();

        // Assert
        assertNotNull(result);
        assertTrue(result.containsKey("dispatchPlan"));
        List<Map<String, Object>> dispatchPlan = (List<Map<String, Object>>) result.get("dispatchPlan");
        List<Map<String, Object>> assignedOrders = (List<Map<String, Object>>) dispatchPlan.get(0).get("assignedOrders");
        assertEquals("ORD002", assignedOrders.get(0).get("orderId")); // High-priority order should be assigned first
    }
}