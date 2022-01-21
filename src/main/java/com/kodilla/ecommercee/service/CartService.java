package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final OrderService orderService;

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

    public Cart getCart(final Long cartId){
        return cartRepository.findById(cartId).get();
    }

    public void updateTotalPrice(final Long cartId, BigDecimal nextItemPrice){
        BigDecimal actualPrice = cartRepository.findById(cartId).get().getTotalPrice();
        cartRepository.findById(cartId).get().setTotalPrice(actualPrice.add(nextItemPrice));
    }
}
