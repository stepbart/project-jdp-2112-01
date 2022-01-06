package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "CARTS")
public class Cart {


    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "CART_ID",unique = true)
    private Long id;

    @NotNull
    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(targetEntity = Item.class, mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Item> items;

    @OneToOne(targetEntity = Order.class, cascade = CascadeType.ALL)
    private Order order;

    public Cart(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        this.items = new ArrayList();
    }
}
