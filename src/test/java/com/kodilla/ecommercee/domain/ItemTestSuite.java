package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTestSuite {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

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
    public void testCreateAndReadItem_And_RelatedEntities() {
        //Given
        Group group = new Group("meble");

        groupRepository.save(group);

        Product product = new Product("stolik", "mały stolik", BigDecimal.valueOf(299L), group);
        User user = createUser();
        userRepository.save(user);
        Cart cart = new Cart(user);

        Item item1 = new Item(4,product,cart);
        Item item2 = new Item(1,product,cart);

        List<Item> newItemsList = new ArrayList<>();
        newItemsList.add(item1);
        newItemsList.add(item2);
        product.setItems(newItemsList);

        cart.getItems().add(item1);
        cart.getItems().add(item2);

        //When
        cartRepository.save(cart);

        //Then
        Long productId = product.getId();
        Long cartId = cart.getId();
        Long item1id = item1.getId();
        Long item2id = item2.getId();

        assertTrue(productRepository.findById(productId).isPresent());
        assertTrue(cartRepository.findById(cartId).isPresent());
        assertEquals(2,productRepository.findById(productId).get().getItems().size());
        assertEquals(2,cartRepository.findById(cartId).get().getItems().size());
        assertTrue(itemRepository.findById(item1id).isPresent());
        assertTrue(itemRepository.findById(item2id).isPresent());

        //CleanUp
        try {
            groupRepository.deleteById(group.getId());
            cartRepository.deleteById(cartId);
            productRepository.deleteById(productId);
            itemRepository.deleteById(item1id);
            itemRepository.deleteById(item2id);
            userRepository.deleteById(user.getId());
        } catch (Exception e) {
        }
    }

    @Test
    public void testUpdateItem_And_RelatedEntities() {
        //Given
        Group group = new Group("meble");

        groupRepository.save(group);

        Product product = new Product("stolik", "mały stolik", BigDecimal.valueOf(299L), group);
        User user = createUser();
        userRepository.save(user);
        Cart cart = new Cart(user);
        Item item = new Item(4,product,cart);

        List<Item> newItemsList = new ArrayList<>();
        newItemsList.add(item);
        product.setItems(newItemsList);

        cart.getItems().add(item);

        cartRepository.save(cart);

        //When
        item.setQuantity(700);

        //Then
        Long productId = product.getId();
        Long cartId = cart.getId();
        Long itemId = item.getId();

        itemRepository.save(item);
        assertEquals(700,itemRepository.findById(itemId).get().getQuantity());
        assertEquals(1,productRepository.findById(productId).get().getItems().size());
        assertEquals(1,cartRepository.findById(cartId).get().getItems().size());

        //CleanUp
        try {
            groupRepository.deleteById(group.getId());
            cartRepository.deleteById(cartId);
            productRepository.deleteById(productId);
            itemRepository.deleteById(itemId);
            userRepository.deleteById(user.getId());
        } catch (Exception e) {
        }
    }

    @Test
    public void testDeleteItem_And_RelatedEntities() {
        //Given
        Group group = new Group("meble");

        groupRepository.save(group);

        Product product = new Product("stolik", "mały stolik", BigDecimal.valueOf(299L), group);
        User user = createUser();
        userRepository.save(user);
        Cart cart = new Cart(user);
        Item item = new Item(4,product,cart);

        List<Item> newItemsList = new ArrayList<>();
        newItemsList.add(item);
        product.setItems(newItemsList);

        cart.getItems().add(item);

        cartRepository.save(cart);

        //When
        Long productId = product.getId();
        Long cartId = cart.getId();
        Long itemId = item.getId();

        itemRepository.deleteById(itemId);

        //Then
        assertFalse(itemRepository.findById(itemId).isPresent());
        assertEquals(0,productRepository.findById(productId).get().getItems().size());
        assertEquals(0,cartRepository.findById(cartId).get().getItems().size());

        //CleanUp
        try {
            groupRepository.deleteById(group.getId());
            cartRepository.deleteById(cartId);
            productRepository.deleteById(productId);
            itemRepository.deleteById(itemId);
            userRepository.deleteById(user.getId());
        } catch (Exception e) {
        }
    }
}