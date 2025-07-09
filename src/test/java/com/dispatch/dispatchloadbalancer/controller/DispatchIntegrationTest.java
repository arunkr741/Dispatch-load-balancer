package com.dispatch.dispatchloadbalancer.controller;

import com.dispatch.dispatchloadbalancer.model.Order;
import com.dispatch.dispatchloadbalancer.model.Priority;
import com.dispatch.dispatchloadbalancer.model.Vehicle;
import com.dispatch.dispatchloadbalancer.repository.OrderRepository;
import com.dispatch.dispatchloadbalancer.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DispatchIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private VehicleRepository vehicleRepository;

    @Test
    public void getDispatchPlan_ShouldReturnValidResponse() throws Exception {
        // Arrange
        List<Order> orders = Arrays.asList(
                new Order("ORD001", 12.2958, 76.6394, "Devaraja Mohalla, Mysore", 10.0, Priority.HIGH)
        );

        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle("VEH001", 100.0, 12.3180, 76.6548, "Vijayanagar, Mysore")
        );

        when(orderRepository.findAll()).thenReturn(orders);
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        // Act and Assert
        mockMvc.perform(get("/api/dispatch/plan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.dispatchPlan").exists());
    }
}