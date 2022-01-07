package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Status;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
                "in preparation",
                user1,
                new Cart()
        );

        this.order2 = new Order(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(7),
                "complete",
                user2,
                new Cart()
        );

        this.order3 = new Order(
                LocalDate.now().minusDays(14),
                LocalDate.now().plusDays(7),
                "delievered",
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

        //CleanUp
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
                "sent",
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

        //When

        //Then

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

        //When

        //Then

        //CleanUp
        try {
            orderRepository.deleteAll();
        } catch (Exception e) {
            System.out.println("Unable to cleanup database");
        }
    }

    @Test
    public void testDeleteOrder() {
        //Given

        //When

        //Then

        //CleanUp
        try {
            orderRepository.deleteAll();
        } catch (Exception e) {
            System.out.println("Unable to cleanup database");
        }
    }

}