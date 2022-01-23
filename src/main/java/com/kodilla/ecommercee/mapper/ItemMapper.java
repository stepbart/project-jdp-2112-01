package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Item;
import com.kodilla.ecommercee.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemMapper {

    public Item mapToItem(final ItemDto itemDto) {
        return new Item(itemDto.getId(), itemDto.getQuantity(), itemDto.getPrice(),itemDto.getProduct(), itemDto.getCart());
    }

    public ItemDto mapToItemDto(final Item item) {
        return new ItemDto(item.getId(), item.getQuantity(), item.getPrice(),item.getProduct(), item.getCart());
    }

    public List<ItemDto> mapToItemDtoList(List<Item> items){
        List<ItemDto> itemsDto = new ArrayList<>();
        for(Item item : items){
            ItemDto itemDto = mapToItemDto(item);
            itemsDto.add(itemDto);
        }
        return itemsDto;
    }
}
