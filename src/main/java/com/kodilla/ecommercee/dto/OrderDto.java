package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private BigDecimal deliveryCost;
    private BigDecimal finalCost;
    private String status;
}
