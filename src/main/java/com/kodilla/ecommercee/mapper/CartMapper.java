package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Item;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ItemDto;

import java.util.ArrayList;
import java.util.List;

public class CartMapper {

    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(cartDto.getId(),cartDto.getUser(), cartDto.getItems(), cartDto.getTotalPrice(), cartDto.getOrder());
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(cart.getId(), cart.getUser(), cart.getItems(), cart.getTotalPrice(), cart.getOrder());
    }

    public List<ItemDto> mapToItemDto(List<Item> items){
        List<ItemDto> itemsDto = new ArrayList<>();
        for(Item item : items){
            ItemDto itemDto = new ItemDto(item.getId(),item.getQuantity(),item.getPrice());
            itemsDto.add(itemDto);
        }
        return itemsDto;
    }
}
