package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ItemRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ItemRepositoryTestSuite {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testCreateAndRead() {
        Product product1 = new Product("stolik","mały stolik",BigDecimal.valueOf(299L),new Group("meble"));
        Product product2 = new Product("telewizor","TV LG555 55cali",BigDecimal.valueOf(1999L),new Group("RTV"));

        //Cart cart1 = new Cart(BigDecimal.valueOf(299L),new User(), new Order());
        //Cart cart2 = new Cart(BigDecimal.valueOf(1999L),new User(), new Order());


//        Item item = new Item();
//
//        Set<Order> orders = new HashSet<>();
//        orders.add(order);
//        Set<Cart> carts = new HashSet<>();
//        carts.add(cart);
//        List<Item> items = new ArrayList<>();
//        items.add(item);
//
//        user.setCarts(carts);
//        user.setOrders(orders);
//        cart.setOrder(order);
//        cart.setUser(user);
//        cart.setItems(items);
//        order.setItems(items);
//        order.setCart(cart);
//        order.setUser(user);
        //item.setOrder(order);
        //item.setCart(cart);

        //itemRepository.save(item);


        //assertEquals(item.getPrice(),new BigDecimal(99.99));
        //assertEquals(item.getQuantity(),8);
    }

//    @Test
//    public void testUpdate() {
//        Item item = new Item();
//        item.setPrice(new BigDecimal(99.99));
//
//        itemRepository.save(item);
//        BigDecimal changedPrice = new BigDecimal(100);
//        Optional<Item> itemEntity = itemRepository
//                .findById(item.getId());
//
//        assertTrue(itemEntity.isPresent());
//        assertEquals(item.getPrice(),new BigDecimal(99.99));
//        assertEquals(item.getQuantity(),9);
//    }
}
