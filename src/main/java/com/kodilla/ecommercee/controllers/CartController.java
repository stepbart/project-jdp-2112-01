package com.kodilla.ecommercee.controllers;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Item;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ItemDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ItemMapper;
import com.kodilla.ecommercee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping(value ="/carts")
public class CartController {

    private final CartMapper cartMapper;
    private final CartService cartService;
    private final ItemMapper itemMapper;

    @PostMapping(value = "/createCart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartService.createCart(cart);
    }

    @GetMapping("getItems/{cartId}")
    public List<ItemDto> getItems(@PathVariable("cartId") final Long cartId) {
        return itemMapper.mapToItemDtoList(cartService.getItems(cartId));
    }

    @PutMapping(value ="/addItem/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addItem(@PathVariable("id") Long cartId, @RequestBody ItemDto itemDto) {
        Item item = itemMapper.mapToItem(itemDto);
        cartService.addItem(cartId,item);
        cartService.updateTotalPrice(cartId,item.getPrice());
    }

    @DeleteMapping(value ="/deleteItem/{cartId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteItem(@PathVariable("cartId") Long cartId, @RequestBody ItemDto itemDto) {
        Item item = itemMapper.mapToItem(itemDto);
        cartService.deleteItem(cartId,item);
    }

    @PostMapping("/createOrder/{cartId}")
    public Order createOrder(
            @PathVariable("cartId") Long cartId,
            @RequestParam(name = "deliveryTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deliveryTime) {
        return cartService.createOrder(cartId,deliveryTime,cartService.getCart(cartId).getUser());
    }
}
