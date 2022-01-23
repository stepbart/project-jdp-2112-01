package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Item;
import com.kodilla.ecommercee.dto.ItemDto;

import org.springframework.stereotype.Service;

@Service
public class ItemMapper {

    public ItemDto mapToItemDto(final Item item, Long productId, Long cartId) {
        return new ItemDto(item.getId(), item.getQuantity(), item.getPrice(),productId,cartId);
    }
}