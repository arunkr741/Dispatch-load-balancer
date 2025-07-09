package com.dispatch.dispatchloadbalancer.service;

import com.dispatch.dispatchloadbalancer.model.Vehicle;
import com.dispatch.dispatchloadbalancer.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class responsible for managing vehicle-related operations.
 * Provides methods for saving and retrieving vehicles.
 */
@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    /**
     * Saves a list of vehicles to the repository.
     *
     * @param vehicles List of vehicles to be saved.
     */
    public void saveVehicles(List<Vehicle> vehicles) {
        vehicleRepository.saveAll(vehicles);
    }

    /**
     * Retrieves all stored vehicles from the repository.
     *
     * @return List of all vehicles.
     */
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}
