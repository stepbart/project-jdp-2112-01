package com.kodilla.ecommercee.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("cartId")
    private Long cartId;

    @JsonProperty("productId")
    private Long productId;
}