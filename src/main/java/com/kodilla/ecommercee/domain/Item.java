package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ITEMS")
public class Item {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ITEM_ID", unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CART_ID")
    private Cart cart;
}
