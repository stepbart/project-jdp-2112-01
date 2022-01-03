package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> temporaryList = new ArrayList<>();

        for (long i = 0; i <= 5; i++) {
            temporaryList.add(new OrderDto(
                    i,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    new BigDecimal(i * i),
                    new BigDecimal(i * i * 2),
                    "Sample status #" + i
            ));
        }

        return new ResponseEntity(
                temporaryList,
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<OrderDto> addNewOrder(@RequestBody final OrderDto order) {
        return new ResponseEntity(
                order,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") final Long id) {
        return new ResponseEntity(
                new OrderDto(
                        1L,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        new BigDecimal(25.00),
                        new BigDecimal(100.00),
                        "Sample status #"
                ),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<OrderDto> updateOrder(@RequestBody final OrderDto order) {
        return new ResponseEntity(
                order,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") final Long id) {
        return new ResponseEntity(
                "Order by id " + id + " was deleted",
                HttpStatus.OK
        );
    }
}
