package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ItemDto;
import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody final CartDto cartDto) {
        return new ResponseEntity(cartDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ItemDto>> getItems(@PathVariable("id") Long cartId) {
        return new ResponseEntity(new ArrayList<ItemDto>(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> addItem(@PathVariable("id") Long cartId, @RequestBody ItemDto itemDto) {
        return new ResponseEntity(itemDto, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/{itemId}")
    public ResponseEntity deleteItem(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable("id") Long cartId) {
        return new ResponseEntity(new CartDto(1L, new BigDecimal(20)), HttpStatus.OK);
    }
}
