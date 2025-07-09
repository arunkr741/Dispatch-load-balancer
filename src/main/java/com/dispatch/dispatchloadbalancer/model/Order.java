package com.dispatch.dispatchloadbalancer.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`order`") // Enclosing order in backticks for H2 compatibility
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    private String orderId;

    private double latitude;
    private double longitude;
    private String address;
    private double packageWeight;

    @Enumerated(EnumType.STRING)
    private Priority priority;
}

