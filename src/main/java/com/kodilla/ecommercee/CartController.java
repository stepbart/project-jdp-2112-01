package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ItemDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private CartMapper cartMapper;
    private CartService cartService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartService.createCart(cart);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ItemDto>> getItems(@PathVariable("id") Long cartId) {
        return new ResponseEntity(new ArrayList<ItemDto>(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> addItem(@PathVariable("id") Long cartId, @RequestBody ItemDto itemDto) {
        return new ResponseEntity(itemDto, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity deleteItem(@PathVariable("cartId") Long cartId, @RequestBody ItemDto itemDto) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable("id") Long cartId) {
        return new ResponseEntity(new OrderDto(1L, LocalDateTime.now(), LocalDateTime.now(), new BigDecimal(20), new BigDecimal(40), "Order created"), HttpStatus.OK);
    }
}
