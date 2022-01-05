package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(
            targetEntity = Item.class,
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Item> items = new ArrayList<>();
}
