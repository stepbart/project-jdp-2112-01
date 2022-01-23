package com.kodilla.ecommercee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.ecommercee.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class OrderDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("orderDate")
    private LocalDate orderDate;
    @JsonProperty("deliveryDate")
    private LocalDate deliveryDate;
    @JsonProperty("finalCost")
    private BigDecimal finalCost;
    @JsonProperty("status")
    private Status status;
}
