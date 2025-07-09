package com.dispatch.dispatchloadbalancer.controller;

import com.dispatch.dispatchloadbalancer.model.Order;
import com.dispatch.dispatchloadbalancer.model.Priority;
import com.dispatch.dispatchloadbalancer.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    public void addOrders_ShouldReturnSuccessMessage() {
        // Arrange
        List<Order> orders = Arrays.asList(
                new Order("ORD001", 12.2958, 76.6394, "Mysore", 10.0, Priority.HIGH)
        );
        Map<String, List<Order>> request = Map.of("orders", orders);

        // Act
        ResponseEntity<Map<String, String>> response = orderController.addOrders(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Delivery orders accepted.", response.getBody().get("message"));
    }
}
