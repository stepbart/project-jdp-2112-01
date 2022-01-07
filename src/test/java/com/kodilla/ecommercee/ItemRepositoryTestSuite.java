package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Item;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void testCreateAndRead() {
        User user = new User("neo","Dan","Brown","test@test.pl","568457984","ul. Testowa 2",false);
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = orderDate.plusDays(3);
        Cart cart = new Cart();
        Order order = new Order(orderDate,deliveryDate,new BigDecimal(99.99),"paid",user,new ArrayList<>(), new HashSet<>());
        Item item = new Item();
        item.setQuantity(8);
        item.setPrice(new BigDecimal(99.99));


        userRepository.save(user);
        orderRepository.save(order);
        item.setOrder(order);
        //item.setCart(cart);
        itemRepository.save(item);

        assertEquals(item.getPrice(),new BigDecimal(99.99));
        assertEquals(item.getQuantity(),8);
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
