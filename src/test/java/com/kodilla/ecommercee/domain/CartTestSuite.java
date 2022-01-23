package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartTestSuite {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    private List<Item> items = new ArrayList<>();

    private User createUser() {
        return new User(
                "testUser",
                "firstName",
                "lastName",
                "test@test.com",
                "11111111",
                "testAddress 1",
                false
        );
    }

    @Test
    public void shouldSaveValueOfCart() {
        //given
        User user = createUser();
        Cart cart = new Cart(user);
        userRepository.save(user);
        //when
        Cart save = cartRepository.save(cart);
        BigDecimal expected = cart.getTotalPrice();
        //then
        assertEquals(expected, save.getTotalPrice());
    }

    @Test
    public void shouldFindItemsInCart() {
        //given
        User user = createUser();
        Cart cart = new Cart(user);
        userRepository.save(user);
        cart.getItems().addAll(items);
        Cart save = cartRepository.save(cart);
        //when
        int expected = cart.getItems().size();
        //then
        assertEquals(expected, save.getItems().size());
    }
    @Test
    public void shouldDeleteCart() {
        //given
        User user = createUser();
        Cart cart = new Cart(user);
        userRepository.save(user);
        cartRepository.save(cart);
        //when
        cartRepository.delete(cart);
        //then
        assertFalse(cartRepository.existsById(cart.getId()));
    }

    @Test
    public void shouldClearAllCartsFromDB() {
        //given
        User user = createUser();
        User user1 = createUser();
        Cart cart = new Cart(user);
        Cart cart1 = new Cart(user1);
        userRepository.save(user);
        userRepository.save(user1);
        cartRepository.save(cart);
        cartRepository.save(cart1);
        //when
        cartRepository.deleteAll();
        //then
        assertEquals(0, cartRepository.findAll().size());
    }

    @Test
    public void testRelationBetweenUserAndCart() {
        //given
        User user = createUser();
        Cart cart = new Cart(user);
        user.getCarts().add(cart);
        userRepository.save(user);
        //when
        //then
        assertTrue(user.getCarts().contains(cart));
    }

    @Test
    public void userShouldNotBeRemovedFromDatabaseIfCartIsDeleted() {
        //given
        User user = createUser();
        Cart cart = new Cart(user);
        user.getCarts().add(cart);
        userRepository.save(user);
        cartRepository.save(cart);
        cartRepository.delete(cart);
        boolean isStill = cartRepository.findAll().contains(cart);
        //when
        Optional<User> expectedUser = userRepository.findById(user.getId());
        //then
        assertTrue(expectedUser.isPresent());
        assertFalse(isStill);
    }
}
