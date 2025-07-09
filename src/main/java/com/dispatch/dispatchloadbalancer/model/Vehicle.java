package com.dispatch.dispatchloadbalancer.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @Id
    private String vehicleId;

    private double capacity;
    private double currentLatitude;
    private double currentLongitude;
    private String currentAddress;
}
