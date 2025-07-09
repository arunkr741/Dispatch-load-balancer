package com.dispatch.dispatchloadbalancer.controller;

import com.dispatch.dispatchloadbalancer.model.Order;
import com.dispatch.dispatchloadbalancer.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing delivery orders.
 * This controller provides endpoints for adding and retrieving orders.
 */
@RestController
@RequestMapping("/api/dispatch/orders")
@RequiredArgsConstructor
public class OrderController {

    // Service responsible for handling order-related operations.
    private final OrderService orderService;

    /**
     * Adds a list of orders to the system.
     *
     * @param request A map containing a list of orders under the key "orders".
     * @return ResponseEntity with a success or error message.
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> addOrders(@RequestBody Map<String, List<Order>> request) {
        List<Order> orders = request.get("orders");

        // Validating each order before saving
        for (Order order : orders) {
            if (order.getLatitude() < -90 || order.getLatitude() > 90 ||
                    order.getLongitude() < -180 || order.getLongitude() > 180) {
                return ResponseEntity.badRequest().body(Map.of("message", "Invalid latitude or longitude."));
            }
            if (order.getPackageWeight() <= 0) {
                return ResponseEntity.badRequest().body(Map.of("message", "Package weight must be positive."));
            }
        }

        // Saving validated orders
        orderService.saveOrders(orders);
        return ResponseEntity.ok(Map.of("message", "Delivery orders accepted.", "status", "success"));
    }

    /**
     * Retrieves all stored delivery orders.
     *
     * @return ResponseEntity containing a list of all orders.
     */
    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
