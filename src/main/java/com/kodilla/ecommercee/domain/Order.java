package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CART_ID")
    private Cart cart;
}
