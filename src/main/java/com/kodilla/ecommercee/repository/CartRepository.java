package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    List<Cart> findById(long id);

    @Override
    List<Cart> findAll();
}
