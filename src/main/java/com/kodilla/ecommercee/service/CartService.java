package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ItemRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final OrderService orderService;
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public Cart createCart(final Long userId) throws UserNotFoundException {
        User user = userService.getUser(userId).orElseThrow(UserNotFoundException::new);
        Cart cart = new Cart(user);
        return cartRepository.save(cart);
    }

    public List<Item> getItems(final Long cartId){
        return cartRepository.findById(cartId).get().getItems();
    }

    public void addItem(final Long cartId, final Long productId, int quantity){
        Item item = new Item(quantity,productRepository.findById(productId).get(), cartRepository.findById(cartId).get());
        itemRepository.save(item);
        BigDecimal itemPrice = item.getPrice();
        BigDecimal actualPrice = cartRepository.findById(cartId).get().getTotalPrice();
        Cart cart = cartRepository.findById(cartId).get();
        cart.setTotalPrice(actualPrice.add(itemPrice));
        cartRepository.save(cart);
    }

    public void deleteItem(final Long cartId, final Long itemId){
        BigDecimal itemPrice = itemRepository.findById(itemId).get().getPrice();
        itemRepository.deleteById(itemId);
        updateTotalPriceAfterDeleting(cartId,itemPrice);
    }

    public Order createOrder(final Long cartId, LocalDate deliveryTime, final User user){
        Order order = new Order(LocalDate.now(),deliveryTime, Status.IN_PREPARATION, user, cartRepository.findById(cartId).get());
        return orderService.addNewOrder(order);
    }

    public Cart getCart(final Long cartId){
        return cartRepository.findById(cartId).get();
    }

    public void updateTotalPriceAfterDeleting(final Long cartId, BigDecimal itemPrice){
        BigDecimal actualPrice = cartRepository.findById(cartId).get().getTotalPrice();
        Cart cart = cartRepository.findById(cartId).get();
        cart.setTotalPrice(actualPrice.subtract(itemPrice));
        cartRepository.save(cart);
    }
}
