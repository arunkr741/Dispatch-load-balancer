package com.dispatch.dispatchloadbalancer.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.dispatch.dispatchloadbalancer.model.Order;
import com.dispatch.dispatchloadbalancer.model.Priority;
import com.dispatch.dispatchloadbalancer.repository.OrderRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void saveOrders_ShouldPersistOrders() {
        // Arrange
        List<Order> orders = Arrays.asList(
                new Order("ORD001", 12.2958, 76.6394, "Mysore", 10.0, Priority.HIGH)
        );
        when(orderRepository.saveAll(orders)).thenReturn(orders);

        // Act
        orderService.saveOrders(orders);

        // Assert
        verify(orderRepository, times(1)).saveAll(orders);
    }

    @Test
    public void getAllOrders_ShouldReturnAllOrders() {
        // Arrange
        List<Order> orders = Arrays.asList(
                new Order("ORD001", 12.2958, 76.6394, "Mysore", 10.0, Priority.HIGH)
        );

        when(orderRepository.findAll()).thenReturn(orders);

        // Act
        List<Order> result = orderService.getAllOrders();

        // Assert
        assertEquals(1, result.size());
        assertEquals("ORD001", result.get(0).getOrderId());
    }
}