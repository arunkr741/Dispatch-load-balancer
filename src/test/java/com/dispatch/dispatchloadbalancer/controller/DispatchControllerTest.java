package com.dispatch.dispatchloadbalancer.controller;

import com.dispatch.dispatchloadbalancer.service.DispatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class DispatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DispatchService dispatchService;

    @Test
    public void getDispatchPlan_ShouldReturnDispatchPlan() throws Exception {
        // Arrange
        Map<String, Object> dispatchPlan = Map.of(
                "dispatchPlan", Collections.emptyList()
        );

        when(dispatchService.generateDispatchPlan()).thenReturn(dispatchPlan);

        // Act and Assert
        mockMvc.perform(get("/api/dispatch/plan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dispatchPlan").exists());
    }

    @Test
    public void getDispatchPlan_ShouldHandleNoOrdersOrVehicles() throws Exception {
        // Arrange
        Map<String, Object> response = Map.of(
                "message", "No orders or vehicles available for dispatch."
        );

        when(dispatchService.generateDispatchPlan()).thenReturn(response);

        // Act and Assert
        mockMvc.perform(get("/api/dispatch/plan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("No orders or vehicles available for dispatch."));
    }

    @Test
    public void getDispatchPlan_ShouldHandleInternalServerError() throws Exception {
        // Arrange
        when(dispatchService.generateDispatchPlan()).thenThrow(new RuntimeException("Internal server error"));

        // Act and Assert
        mockMvc.perform(get("/api/dispatch/plan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$").value("An error occurred: Internal server error"));
    }
}