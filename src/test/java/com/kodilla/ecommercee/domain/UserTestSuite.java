package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestSuite extends TestCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    public User generateUser(String firstName, String lastName) {
        return new User(
                "testUser",
                firstName,
                lastName,
                "test@test.com",
                "888888888",
                "testAddress 5",
                false
        );
    }

    @Test
    public void testCreateUser() {
        // given
        User user = generateUser("Jan", "Nowak");

        // when
        User saveUser = userRepository.save(user);

        // then
        assertNotNull(saveUser.getId());

        //clean up
        userRepository.deleteById(saveUser.getId());
    }

    @Test
    public void testSearchUserById() {
        // given
        User user = generateUser("Jan", "Nowak");
        User savedUser = userRepository.save(user);

        // when
        User result = userRepository.findById(savedUser.getId()).get();

        // then
        assertNotNull(result.getId());
        assertEquals("Jan", result.getFirstName());
        assertEquals("Nowak", result.getLastName());

        //clean up
        userRepository.deleteById(savedUser.getId());
    }

    @Test
    public void updateUserTest() {
        // given
        User user = generateUser("Jan", "Nowak");
        Long id = userRepository.save(user).getId();

        // when
        User searchUser = userRepository.findById(id).get();
        searchUser.setFirstName("Andrzej");
        searchUser.setLastName("Sprzęgło");

        // then
        assertEquals("Andrzej", searchUser.getFirstName());
        assertEquals("Sprzęgło", searchUser.getLastName());

        //clean up
        userRepository.deleteById(id);
    }

    @Test
    public void deleteUserTest() {
        // given
        Cart cart = new Cart(new BigDecimal(25.55));
        User user = generateUser("Jan", "Nowak");
        user.addCart(cart);
        cart.setUser(user);
        user = userRepository.save(user);
        Long idCart = cart.getId();

        // when
        userRepository.deleteById(user.getId());

        // then
        assertFalse(userRepository.existsById(user.getId()));
        assertFalse(cartRepository.existsById(idCart));
    }
}