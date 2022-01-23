package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    @Builder
    public Order mapToOrder(final OrderDto orderDto) {
        OrderBuilder orderBuilder = new OrderBuilder().orderDto(orderDto);
        Order build = orderBuilder.build();
        return build;
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderDate(),
                order.getDeliveryDate(),
                order.getFinalCost(),
                order.getStatus()
        );
    }

    public List<OrderDto> mapToDtoList(final List<Order> orders) {
        return orders.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
