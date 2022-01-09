package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

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

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    public Item(int quantity, Product product, Cart cart) {
        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
        this.price = product.getUnitPrice().multiply(new BigDecimal(quantity));
    }

    public void calculatePrice(Product product){
        if (this.quantity > 0){
            this.price = product.getUnitPrice().multiply(new BigDecimal(this.quantity));
        }
        else {
            this.price = new BigDecimal(BigInteger.ZERO);
        }
    }
}
