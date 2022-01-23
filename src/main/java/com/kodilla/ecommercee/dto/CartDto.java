package com.kodilla.ecommercee.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.ecommercee.domain.Item;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user")
    private User user;

    @JsonProperty("items")
    private List<Item> items;

    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

    @JsonProperty("order")
    private Order order;
}
