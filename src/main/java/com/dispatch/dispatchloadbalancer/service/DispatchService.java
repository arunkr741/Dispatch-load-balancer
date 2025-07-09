package com.dispatch.dispatchloadbalancer.service;

import com.dispatch.dispatchloadbalancer.model.Order;
import com.dispatch.dispatchloadbalancer.model.Vehicle;
import com.dispatch.dispatchloadbalancer.repository.OrderRepository;
import com.dispatch.dispatchloadbalancer.repository.VehicleRepository;
import com.dispatch.dispatchloadbalancer.util.HaversineUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service responsible for dispatching orders to available vehicles efficiently.
 * Implements order assignment based on vehicle capacity and distance optimization.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DispatchService {
    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;

    /**
     * Saves a list of incoming orders to the repository.
     *
     * @param orders List of orders to be saved.
     */
    public void addOrders(List<Order> orders) {
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

    /**
     * Generates an optimized dispatch plan by assigning orders to vehicles
     * based on priority, capacity constraints, and distance calculations.
     *
     * @return A map containing the status and the generated dispatch plan.
     */
    public Map<String, Object> generateDispatchPlan() {
        log.info("Starting dispatch plan generation...");

        List<Order> orders = new ArrayList<>(orderRepository.findAll());
        List<Vehicle> vehicles = new ArrayList<>(vehicleRepository.findAll());

        // Check if there are available orders and vehicles
        if (orders.isEmpty() || vehicles.isEmpty()) {
            log.warn("No orders or vehicles available for dispatch.");
            return Map.of("status", "error", "message", "No orders or vehicles available for dispatch.");
        }

        log.info("Processing {} orders and {} vehicles", orders.size(), vehicles.size());

        // Sort orders by priority (HIGH → MEDIUM → LOW)
        orders.sort(Comparator.comparing(o -> o.getPriority().ordinal()));

        List<Map<String, Object>> vehicleAssignments = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            double remainingCapacity = vehicle.getCapacity();
            double totalDistance = 0;
            List<Map<String, Object>> assignedOrders = new ArrayList<>();
            double lastLat = vehicle.getCurrentLatitude();
            double lastLon = vehicle.getCurrentLongitude();

            log.info("Assigning orders to vehicle ID: {}", vehicle.getVehicleId());

            Iterator<Order> iterator = orders.iterator();
            while (iterator.hasNext()) {
                Order order = iterator.next();

                // Ensure the order fits within the vehicle's remaining capacity
                if (order.getPackageWeight() <= remainingCapacity) {
                    double distance = HaversineUtil.calculateDistance(lastLat, lastLon, order.getLatitude(), order.getLongitude());
                    totalDistance += distance;

                    // Store assigned order details
                    assignedOrders.add(Map.of(
                            "orderId", order.getOrderId(),
                            "latitude", order.getLatitude(),
                            "longitude", order.getLongitude(),
                            "address", order.getAddress(),
                            "packageWeight", order.getPackageWeight(),
                            "priority", order.getPriority().toString()
                    ));

                    log.info("Assigned order ID: {} to vehicle ID: {} (Remaining capacity: {})",
                            order.getOrderId(), vehicle.getVehicleId(), remainingCapacity - order.getPackageWeight());

                    lastLat = order.getLatitude();
                    lastLon = order.getLongitude();
                    remainingCapacity -= order.getPackageWeight();
                    iterator.remove();
                }
            }

            log.info("Vehicle ID: {} assigned {} orders, total distance: {:.2f} km",
                    vehicle.getVehicleId(), assignedOrders.size(), totalDistance);

            // Store vehicle assignment summary
            vehicleAssignments.add(Map.of(
                    "vehicleId", vehicle.getVehicleId(),
                    "totalLoad", vehicle.getCapacity() - remainingCapacity,
                    "totalDistance", String.format("%.2f km", totalDistance),
                    "assignedOrders", assignedOrders
            ));
        }

        log.info("Dispatch plan generation completed successfully.");
        return Map.of("status", "success", "dispatchPlan", vehicleAssignments);
    }
}
