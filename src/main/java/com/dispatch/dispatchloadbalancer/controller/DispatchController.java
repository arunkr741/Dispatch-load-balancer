package com.dispatch.dispatchloadbalancer.controller;

import com.dispatch.dispatchloadbalancer.service.DispatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST controller for handling dispatch-related requests.
 * This controller provides an endpoint to retrieve the optimized dispatch plan.
 */
@RestController
@RequestMapping("/api/dispatch")
@RequiredArgsConstructor
public class DispatchController {

    // Service responsible for dispatch optimization logic.
    private final DispatchService dispatchService;

    /**
     * Retrieves the optimized dispatch plan.
     *
     * @return ResponseEntity containing the dispatch plan as a map.
     */
    @GetMapping("/plan")
    public ResponseEntity<Map<String, Object>> getDispatchPlan() {
        Map<String, Object> dispatchPlan = dispatchService.generateDispatchPlan();
        return ResponseEntity.ok(dispatchPlan); // Returns the generated dispatch plan.
    }
}
