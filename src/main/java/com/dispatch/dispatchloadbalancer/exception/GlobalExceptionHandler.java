package com.dispatch.dispatchloadbalancer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Global exception handler for handling application-wide exceptions.
 * This ensures consistent error responses for different types of exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions when an order is not found.
     * Returns a 404 Not Found response with an appropriate message.
     *
     * @param ex the exception thrown when an order is missing
     * @return ResponseEntity with a 404 status and error message
     */
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFound(OrderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles exceptions when a vehicle is not found.
     * Returns a 404 Not Found response with an appropriate message.
     *
     * @param ex the exception thrown when a vehicle is missing
     * @return ResponseEntity with a 404 status and error message
     */
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<String> handleVehicleNotFound(VehicleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles generic exceptions that are not explicitly handled.
     * Returns a 500 Internal Server Error response with an error message.
     *
     * @param ex the generic exception
     * @return ResponseEntity with a 500 status and error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }

    /**
     * Creates a structured error response with a timestamp, status code, error type, and message.
     *
     * @param message the error message
     * @param status the HTTP status of the error
     * @return a map containing error details
     */
    private Map<String, Object> createErrorResponse(String message, HttpStatus status) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", message
        );
    }
}
