package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ItemRepository extends CrudRepository<Item, Long> {
}
