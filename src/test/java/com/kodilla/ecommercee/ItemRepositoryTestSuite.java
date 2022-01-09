package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.*;
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

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveItemAndRelatedProductEntity() {
        Product product1 = new Product("stolik", "mały stolik", BigDecimal.valueOf(299L), new Group("meble"));

        Item item1 = new Item();
        item1.setQuantity(4);
        item1.calculatePrice(product1);
        item1.setProduct(product1);

        Item item2 = new Item();
        item2.setQuantity(1);
        item2.calculatePrice(product1);
        item2.setProduct(product1);

        List<Item> newItemsList = new ArrayList<>();
        newItemsList.add(item1);
        newItemsList.add(item2);
        product1.setItems(newItemsList);



        itemRepository.save(item1);
        itemRepository.save(item2);
        long product1id = product1.getId();
        System.out.println(productRepository.findById(product1id));
        Product testProduct = productRepository.findById(product1id).orElse(null);
        testProduct.getItems().stream().map(Item::getPrice).forEach(System.out::println);
        //itemRepository.save(item1);



    }}