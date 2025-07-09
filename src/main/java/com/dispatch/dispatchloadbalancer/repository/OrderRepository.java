package com.dispatch.dispatchloadbalancer.repository;

import com.dispatch.dispatchloadbalancer.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Order entities in the database.
 * Extends JpaRepository to provide built-in CRUD operations.
 */
public interface OrderRepository extends JpaRepository<Order, String> {
}
