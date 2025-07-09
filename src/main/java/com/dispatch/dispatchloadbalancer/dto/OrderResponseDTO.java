package com.dispatch.dispatchloadbalancer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDTO {
    private String orderId;
    private double latitude;
    private double longitude;
    private String address;
    private double packageWeight;
    private String priority;
}
