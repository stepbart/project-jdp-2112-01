package com.kodilla.ecommercee.domain;

import javax.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
