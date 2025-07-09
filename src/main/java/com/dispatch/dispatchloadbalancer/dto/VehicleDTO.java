package com.dispatch.dispatchloadbalancer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleDTO {
    private String vehicleId;
    private double capacity;
    private double currentLatitude;
    private double currentLongitude;
    private String currentAddress;
}
