package com.kodilla.ecommercee.controllers;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET, value = "getOrder/{id}")
    public OrderDto getOrder(@PathVariable("id") final Long id) throws OrderNotFoundException {
        Order order = orderService.findOrder(id).orElseThrow(OrderNotFoundException::new);
        return orderMapper.mapToOrderDto(order);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody final OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        Order toSave = orderService.addNewOrder(order);
        return orderMapper.mapToOrderDto(toSave);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder/{id}")
    public void deleteOrder(@PathVariable("id") final Long id) {
        orderService.deleteOrderById(id);
    }
}
