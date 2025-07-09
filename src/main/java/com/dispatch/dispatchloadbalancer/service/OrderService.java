package com.dispatch.dispatchloadbalancer.service;

import com.dispatch.dispatchloadbalancer.model.Order;
import com.dispatch.dispatchloadbalancer.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class responsible for managing order-related operations.
 * Provides methods for saving and retrieving orders.
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    /**
     * Saves a list of orders to the repository.
     *
     * @param orders List of orders to be saved.
     */
    public void saveOrders(List<Order> orders) {
        orderRepository.saveAll(orders);
    }

    /**
     * Retrieves all stored orders from the repository.
     *
     * @return List of all orders.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
