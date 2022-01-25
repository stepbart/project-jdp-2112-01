package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "CART_ID",unique = true)
    private Long id;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            targetEntity = Item.class,
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Item> items;

    @OneToOne
    private Order order;

    public Cart(User user) {
        this.user = user;
        this.items = new ArrayList<>();
        this.totalPrice = BigDecimal.ZERO;
    }

    public Cart(Long id, BigDecimal totalPrice, User user, Order order) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.user = user;
        this.order = order;
    }
}