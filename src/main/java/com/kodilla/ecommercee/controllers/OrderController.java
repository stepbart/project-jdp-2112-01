package com.kodilla.ecommercee.controllers;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders() {
        List<Order> allOrders = orderService.getAllOrders();
        return orderMapper.mapToDtoList(allOrders);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewOrder(@RequestBody final OrderDto order) {
        Order orderToSave = orderMapper.mapToOrder(order);
        orderService.addNewOrder(orderToSave);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(@PathVariable("id") final Long id) {
        OrderDto orderDto = orderMapper.mapToOrderDto(orderService.findOrder(id).orElse(null));
        return orderDto;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody final OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        Order toSave = orderService.addNewOrder(order);
        return orderMapper.mapToOrderDto(toSave);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(@PathVariable("id") final Long id) {
        orderService.deleteOrderById(id);
    }
}
