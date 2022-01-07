package com.kodilla.ecommercee.cart;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Item;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CartTestSuite {

    private User user;
    private Order order;
    private Item item;
    private Cart cart;

    private List<Item> fillList() {
        Random random = new Random();
        int quantity = random.nextInt(99);
        int value = random.nextInt(3000);
        List<Item> items = new ArrayList<>();
        for (Long i = 0L; i <= 20L; i++) {
            items.add(new Item(
                    i,
                    quantity,
                    new BigDecimal(BigInteger.valueOf(value)),
                    null,
                    null
            ));
        }
        return items;
    }

    @Test
    void shouldReturnListOfItemsInCart() {
        //given
        List<Item> items = fillList();
        //when

        //then
    }
}
