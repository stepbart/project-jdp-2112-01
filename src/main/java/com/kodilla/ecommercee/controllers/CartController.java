package com.kodilla.ecommercee.controllers;

import com.kodilla.ecommercee.dto.ItemDto;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ItemMapper;
import com.kodilla.ecommercee.repository.ItemRepository;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

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
    private final UserService userService;
    private final ItemRepository itemRepository;

    @PostMapping(value = "/createCart/{userId}")
    public void createCart(@PathVariable("userId") Long userId) throws UserNotFoundException {
        cartService.createCart(userId);
    }

    @GetMapping("/getItems/{cartId}")
    public List<ItemDto> getItems(@PathVariable("cartId") final Long cartId) {
        return cartService.getItems(cartId);
    }

    @PostMapping(value ="/addItem/{id}")
    public void addItem(@PathVariable("id") Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
        cartService.addItem(cartId,productId,quantity);
    }

    @DeleteMapping(value ="/deleteItem/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        cartService.deleteItem(itemId);
    }

    @PostMapping("/createOrder/{cartId}")
    public void createOrder(
            @PathVariable("cartId") Long cartId,
            @RequestParam(name = "deliveryTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deliveryTime) {
        cartService.createOrder(cartId,deliveryTime,cartService.getCart(cartId).getUser());
    }
}
