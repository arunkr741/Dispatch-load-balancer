package com.dispatch.dispatchloadbalancer.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.dispatch.dispatchloadbalancer.model.Vehicle;
import com.dispatch.dispatchloadbalancer.repository.VehicleRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    public void saveVehicles_ShouldPersistVehicles() {
        // Arrange
        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle("VEH001", 100.0, 12.2958, 76.6394, "Mysore")
        );

        when(vehicleRepository.saveAll(vehicles)).thenReturn(vehicles);

        // Act
        vehicleService.saveVehicles(vehicles);

        // Assert
        verify(vehicleRepository, times(1)).saveAll(vehicles);
    }

    @Test
    public void getAllVehicles_ShouldReturnAllVehicles() {
        // Arrange
        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle("VEH001", 100.0, 12.2958, 76.6394, "Mysore")
        );

        when(vehicleRepository.findAll()).thenReturn(vehicles);

        // Act
        List<Vehicle> result = vehicleService.getAllVehicles();

        // Assert
        assertEquals(1, result.size());
        assertEquals("VEH001", result.get(0).getVehicleId());
    }
}