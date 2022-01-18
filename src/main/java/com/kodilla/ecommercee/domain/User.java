package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(name = "isBlocked", nullable = false)
    private boolean isBlocked;

    @GeneratedValue
    @Column(name = "userRandomKey")
    private String randomKey = generateRandomKey();

    @OneToMany(
            targetEntity = Cart.class,
            mappedBy = "user",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.REMOVE
            }
    )
    private Set<Cart> carts;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private Set<Order> orders;

    public User(String userName, String firstName, String lastName, String email, String phoneNumber, String address, boolean isBlocked) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.isBlocked = isBlocked;
        this.carts = new HashSet<>();
        this.orders = new HashSet<>();
        this.randomKey = generateRandomKey();
    }

    public User(Long id, String userName, String firstName, String lastName, String email, String phoneNumber, String address, boolean isBlocked, String randomKey, Set<Cart> carts, Set<Order> orders) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.isBlocked = isBlocked;
        this.randomKey = generateRandomKey();
        this.carts = carts;
        this.orders = orders;
    }

    public void addCart(Cart cart) {
        carts.add(cart);
    }

    private String generateRandomKey(){
        Random random = new Random();
        return Integer.toString(random.nextInt(99999999));
    }
}
