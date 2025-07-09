package com.dispatch.dispatchloadbalancer.repository;

import com.dispatch.dispatchloadbalancer.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Vehicle entities in the database.
 * Extends JpaRepository to provide built-in CRUD operations.
 */
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
}
