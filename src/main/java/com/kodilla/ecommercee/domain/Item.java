package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ITEMS")
public class Item {

    @Id
    @GeneratedValue
    @Column(name="id", nullable = false, unique = true)
    private Long id;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @Column(name="price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

}
