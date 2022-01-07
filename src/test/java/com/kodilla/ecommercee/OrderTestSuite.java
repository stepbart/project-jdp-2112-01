package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class OrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private Order order1;
    private Order order2;
    private Order order3;

    @Before
    public void prepareUsersAndOrders() {
       this.user1 = new User(
                "user1",
                "Jan",
                "Kowalski",
                "jan@kowalski.com",
                "123456789",
                "ul. Spoldzielcza 13, Warszawa",
                false);
        this.user2 = new User(
                "user2",
                "Grażyna",
                "Nowak",
                "grażyna@nowak.com",
                "123456789",
                "ul. Więzienna 21, Wrocław",
                false);
        userRepository.save(user1);
        userRepository.save(user2);

        this.order1 = new Order(
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                Status.IN_PREPARATION,
                user1,
                new Cart()
        );

        this.order2 = new Order(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(7),
                Status.COMPLETE,
                user2,
                new Cart()
        );

        this.order3 = new Order(
                LocalDate.now().minusDays(14),
                LocalDate.now().plusDays(7),
                Status.DELIEVERED,
                user1,
                new Cart()
        );

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
    }

    @After
    public void cleanUpUsers() {
        try {
            userRepository.deleteAll();
        } catch (Exception e) {
            System.out.println("Unable to cleanup database");
        }
    }

    @Test
    public void testGetAllOrders() {
        //Given

        //When
        int result = orderRepository.findAll().size();

        //Then
        assertEquals(3,result);

//        CleanUp
        try {
            orderRepository.deleteAll();
        } catch (Exception e) {
            System.out.println("Unable to cleanup database");
        }
    }

    @Test
    public void testAddNewOrder() {
        //Given
        Order order = new Order(
                LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(7),
                Status.SENT,
                user2,
                new Cart()
        );
        //When
        orderRepository.save(order);
        int result = orderRepository.findAll().size();

        //Then
        assertEquals(4, result);
        //CleanUp
        try {
            orderRepository.deleteById(order.getId());
        } catch (Exception e) {
            System.out.println("Unable to cleanup database");
        }
    }

    @Test
    public void testDisplayOrder() {
        //Given
        Group group = new Group("gorceries");
        Product product1 = new Product("milk", "3,2%", new BigDecimal(2.19), group);
        Product product2 = new Product("bread", "whole grain", new BigDecimal(6.00), group);
        Product product3 = new Product("butter", "500g", new BigDecimal(12.01), group);

        Cart cart = new Cart(new BigDecimal(0));
        Item item1 = new Item(10, product1, cart);
        Item item2 = new Item(3, product2, cart);
        Item item3 = new Item(1, product3, cart);
        cart.getItems().add(item1);
        cart.getItems().add(item2);
        cart.getItems().add(item3);

        Order order = new Order(
                LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(7),
                Status.IN_PREPARATION,
                user2,
                cart);

        orderRepository.save(order);
        long orderId = order.getId();

        //When
        int numberOfProductsInCart = orderRepository.findById(orderId).get().getCart().getItems().size();
        BigDecimal totalPrice = orderRepository.findById(orderId).get().getCart().getItems().stream()
                        .map(item -> item.getPrice())
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        //Then
        assertEquals(3, numberOfProductsInCart);
        assertEquals((new BigDecimal(51.91)).setScale(2, RoundingMode.CEILING), totalPrice);

        //CleanUp
        try {
            orderRepository.deleteAll();
        } catch (Exception e) {
            System.out.println("Unable to cleanup database");
        }
    }

    @Test
    public void testUpdateOrder() {
        //Given
        Order order = new Order(
                LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(7),
                Status.SENT,
                user2,
                new Cart()
        );
        orderRepository.save(order);
        long orderId = order.getId();

        //When
        Order updatedOrder = orderRepository.findById(orderId).get();
        updatedOrder.setStatus(Status.DELIEVERED);
        orderRepository.save(updatedOrder);

        //Then
        assertEquals(Status.DELIEVERED, orderRepository.findById(orderId).get().getStatus());

        //CleanUp
        try {
            orderRepository.deleteById(orderId);
        } catch (Exception e) {
            System.out.println("Unable to cleanup database");
        }
    }

    @Test
    public void testDeleteOrder() {
        //Given
        long orderId = order2.getId();

        //When
        orderRepository.deleteById(orderId);

        //Then
        assertEquals(2, orderRepository.findAll().size());
        assertFalse(orderRepository.existsById(orderId));

        //CleanUp
        try {
            orderRepository.deleteAll();
        } catch (Exception e) {
            System.out.println("Unable to cleanup database");
        }
    }

}