package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;

public class CartMapper {

    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(cartDto.getId(),cartDto.getUser(), cartDto.getItems(), cartDto.getTotalPrice(), cartDto.getOrder());
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(cart.getId(), cart.getUser(), cart.getItems(), cart.getTotalPrice(), cart.getOrder());
    }
}
