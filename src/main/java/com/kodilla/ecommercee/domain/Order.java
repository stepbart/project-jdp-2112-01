package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @Column(name="id", nullable = false, unique = true)
    private Long id;

    @Column(name = "orderDate")
    private LocalDate orderDate;

    @Column(name = "deliveryDate")
    private LocalDate deliveryDate;

    @Column(name = "finalCost")
    private BigDecimal finalCost;

    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    public Order(LocalDate orderDate, LocalDate deliveryDate, Status status, User user, Cart cart) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.finalCost = cart.getTotalPrice();
        this.status = status;
        this.user = user;
        this.cart = cart;
    }
}
