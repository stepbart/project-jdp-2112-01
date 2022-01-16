package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Item;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class CartDto {

    private Long id;
    private User user;
    private List<Item> items;
    private BigDecimal totalPrice;
    private Order order;
}
