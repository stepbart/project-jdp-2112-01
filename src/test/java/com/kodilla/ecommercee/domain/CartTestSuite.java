package com.kodilla.ecommercee.cart;

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

    private Cart createOne(BigDecimal totalValue) {
        return new Cart(
                totalValue
        );
    }

    @Test
    public void shouldSaveValueOfCart() {
        //given
        Cart cart = createOne(new BigDecimal("490.43"));
        //when
        Cart save = cartRepository.save(cart);
        BigDecimal expected = new BigDecimal("490.43");
        //then
        assertEquals(expected, save.getTotalPrice());
    }

    @Test
    public void shouldFindItemsInCart() {
        //given
        Cart cart = createOne(new BigDecimal("199.05"));
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
        Cart cart = createOne(new BigDecimal("199.05"));
        cartRepository.save(cart);
        //when
        cartRepository.delete(cart);
        //then
        assertFalse(cartRepository.existsById(cart.getId()));
    }

    @Test
    public void shouldClearAllCartsFromDB() {
        //given
        Cart cart = createOne(new BigDecimal("199.05"));
        Cart cart1 = createOne(new BigDecimal("310.20"));
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
        Cart cart = createOne(new BigDecimal("199.05"));
        User user = createUser();
        user.getCarts().add(cart);
        cartRepository.save(cart);
        //when
        //then
        assertTrue(user.getCarts().contains(cart));
    }

    @Test
    public void shouldUserBeRemovedFromDatabaseIfCartIsDeleted() {
        //given
        Cart cart = createOne(new BigDecimal("199.05"));
        User user = createUser();
        user.getCarts().add(cart);
        cartRepository.save(cart);
        cartRepository.delete(cart);
        boolean isStill = cartRepository.findAll().contains(cart);
        //when
        Optional<User> expectedUser = userRepository.findById(1L);
        //then
        assertEquals(Optional.empty(), expectedUser);
        assertFalse(isStill);
    }

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
}
