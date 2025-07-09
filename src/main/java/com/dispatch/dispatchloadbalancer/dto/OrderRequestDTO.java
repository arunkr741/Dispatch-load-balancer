package com.dispatch.dispatchloadbalancer.dto;

import lombok.Data;

@Data
public class OrderRequestDTO {
    private double latitude;
    private double longitude;
    private String address;
    private double packageWeight;
    private String priority; // HIGH, MEDIUM, LOW
}

