package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;

public class CartService {

    private CartRepository cartRepository;
    private OrderService orderService;

    public Cart createCart(final Cart cart){
        return cartRepository.save(cart);
    }

    public List<Item> getItems(final Long cartId){
        return cartRepository.findById(cartId).get().getItems();
    }

    public boolean addItem(final Long cartId, final Item item){
        return cartRepository.findById(cartId).get().getItems().add(item);
    }

    public boolean deleteItem(final Long cartId, final Item item){
        return cartRepository.findById(cartId).get().getItems().remove(item);
    }

    public Order createOrder(final Long cartId, LocalDate deliveryTime, final User user){
        Order order = new Order(LocalDate.now(),deliveryTime, Status.IN_PREPARATION, user, cartRepository.findById(cartId).get());
        return orderService.addNewOrder(order);
    }

}
