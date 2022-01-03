package com.kodilla.ecommercee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public ResponseEntity<List<GenericEntity>> getAllOrders() {
        List<GenericEntity> temporaryList = Arrays.asList(
                new GenericEntity("Order 1"),
                new GenericEntity("Order 2"),
                new GenericEntity("Order 3")
        );

        return new ResponseEntity(
                temporaryList,
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<GenericEntity> addNewOrder(@RequestBody final GenericEntity genericEntity) {
        return new ResponseEntity(
                genericEntity + " is created",
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericEntity> getOrder(@PathVariable("id") final Long id) {
        return new ResponseEntity(
                "Order by id: " + new GenericEntity("Searching order by id"),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<Object> updateOrder(@RequestBody final GenericEntity genericEntity) {
        return new ResponseEntity(
                "Updated order: " + genericEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericEntity> deleteOrder(@PathVariable("id") final Long id) {
        return new ResponseEntity(
                "Order by id " + id + " was deleted",
                HttpStatus.OK
        );
    }
}
