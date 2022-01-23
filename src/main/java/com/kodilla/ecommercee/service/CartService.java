package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.dto.ItemDto;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.ItemMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ItemRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final OrderService orderService;
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final ItemMapper itemMapper;

    public Cart createCart(final Long userId) throws UserNotFoundException {
        User user = userService.getUser(userId).orElseThrow(UserNotFoundException::new);
        Cart cart = new Cart(user);
        return cartRepository.save(cart);
    }

    public List<ItemDto> getItems(final Long cartId){
        List<Item> items = cartRepository.findById(cartId).get().getItems();
        List<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items){
            ItemDto itemDto = itemMapper.mapToItemDto(item,item.getProduct().getId(),item.getCart().getId());
            itemsDto.add(itemDto);
        }
        return itemsDto;
    }

    public void addItem(final Long cartId, final Long productId, int quantity){
        Item item = new Item(quantity,productRepository.findById(productId).get(), cartRepository.findById(cartId).get());
        itemRepository.save(item);
        BigDecimal itemPrice = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
        BigDecimal actualPrice = cartRepository.findById(cartId).get().getTotalPrice();
        Cart cart = cartRepository.findById(cartId).get();
        cart.setTotalPrice(actualPrice.add(itemPrice));
        cartRepository.save(cart);
    }

    public void deleteItem(final Long itemId){
        itemRepository.deleteById(itemId);
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