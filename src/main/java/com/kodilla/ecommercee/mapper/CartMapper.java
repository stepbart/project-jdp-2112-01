package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {

    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(cartDto.getId(), cartDto.getTotalPrice(), cartDto.getUser(), cartDto.getItems(), cartDto.getOrder());
    }
}
