package com.kodilla.ecommercee;

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
@SpringBootTest()
public class ItemTestSuite {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreateAndReadItem_And_RelatedEntities() {
        //Given
        Product product = new Product("stolik", "mały stolik", BigDecimal.valueOf(299L), new Group("meble"));
        Cart cart = new Cart(new BigDecimal(1500));

        Item item1 = new Item(4,cart,new BigDecimal(1000));
        Item item2 = new Item(1,cart,new BigDecimal(500));

        Item item3 = new Item();
        item3.setQuantity(4);
        item3.setProduct(product);

        Item item4 = new Item();
        item4.setQuantity(1);
        item4.setProduct(product);

        item3.calculatePrice(product);
        item4.calculatePrice(product);

        List<Item> newItemsList = new ArrayList<>();
        newItemsList.add(item3);
        newItemsList.add(item4);
        product.setItems(newItemsList);

        cart.getItems().add(item1);
        cart.getItems().add(item2);

        //When
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);

        //Then
        long productId = product.getId();
        long cartId = cart.getId();
        Long item1id = item1.getId();
        Long item2id = item2.getId();
        Long item3id = item3.getId();
        Long item4id = item4.getId();

        Product testProduct = productRepository.findById(productId).orElse(null);
        Cart testCart = cartRepository.findById(cartId).orElse(null);

        assertTrue(productRepository.findById(productId).isPresent());
        assertTrue(cartRepository.findById(cartId).isPresent());
        assertEquals(2,productRepository.findById(productId).get().getItems().size());
        assertEquals(2,cartRepository.findById(cartId).get().getItems().size());
        assertTrue(itemRepository.findById(item1id).isPresent());
        assertTrue(itemRepository.findById(item2id).isPresent());
        assertTrue(itemRepository.findById(item3id).isPresent());
        assertTrue(itemRepository.findById(item4id).isPresent());
    }

    @Test
    public void testUpdateItem_And_RelatedEntities() {
        //Given
        Product product = new Product("stolik", "mały stolik", BigDecimal.valueOf(299L), new Group("meble"));

        Cart cart = new Cart(new BigDecimal(1500));

        Item item = new Item();
        item.setQuantity(4);
        item.setProduct(product);
        item.setCart(cart);
        item.calculatePrice(product);

        List<Item> newItemsList = new ArrayList<>();
        newItemsList.add(item);
        product.setItems(newItemsList);

        cart.getItems().add(item);

        itemRepository.save(item);

        //When
        item.setQuantity(700);

        //Then
        long productId = product.getId();
        long cartId = cart.getId();
        Long itemId = item.getId();

        itemRepository.save(item);
        assertEquals(700,itemRepository.findById(itemId).get().getQuantity());
        assertEquals(1,productRepository.findById(productId).get().getItems().size());
        assertEquals(1,cartRepository.findById(cartId).get().getItems().size());
    }

    @Test
    public void testDeletItem_And_RelatedEntities() {
        //Given
        Product product = new Product("stolik", "mały stolik", BigDecimal.valueOf(299L), new Group("meble"));

        Cart cart = new Cart(new BigDecimal(1500));

        Item item = new Item();
        item.setQuantity(4);
        item.setProduct(product);
        item.setCart(cart);
        item.calculatePrice(product);

        List<Item> newItemsList = new ArrayList<>();
        newItemsList.add(item);
        product.setItems(newItemsList);

        cart.getItems().add(item);

        itemRepository.save(item);

        //When
        long productId = product.getId();
        long cartId = cart.getId();
        Long itemId = item.getId();

        itemRepository.deleteById(itemId);

        //Then
        assertFalse(itemRepository.findById(itemId).isPresent());
        assertEquals(0,productRepository.findById(productId).get().getItems().size());
        assertEquals(0,cartRepository.findById(cartId).get().getItems().size());
    }
}