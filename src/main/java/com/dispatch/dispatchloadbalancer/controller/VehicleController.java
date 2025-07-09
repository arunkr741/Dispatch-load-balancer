package com.dispatch.dispatchloadbalancer.controller;

import com.dispatch.dispatchloadbalancer.model.Vehicle;
import com.dispatch.dispatchloadbalancer.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing vehicle data.
 * Provides endpoints for adding and retrieving vehicles.
 */
@RestController
@RequestMapping("/api/dispatch/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    // Service responsible for handling vehicle-related operations.
    private final VehicleService vehicleService;

    /**
     * Adds a list of vehicles to the system.
     *
     * @param request A map containing a list of vehicles under the key "vehicles".
     * @return ResponseEntity with a success message upon successful addition.
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> addVehicles(@RequestBody Map<String, List<Vehicle>> request) {
        List<Vehicle> vehicles = request.get("vehicles");

        // Save the vehicle details in the system
        vehicleService.saveVehicles(vehicles);
        return ResponseEntity.ok(Map.of("message", "Vehicle details accepted.", "status", "success"));
    }

    /**
     * Retrieves all registered vehicles.
     *
     * @return ResponseEntity containing a list of all vehicles.
     */
    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }
}
