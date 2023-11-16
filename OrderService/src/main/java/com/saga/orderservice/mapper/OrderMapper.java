package com.saga.orderservice.mapper;

import com.saga.orderservice.dto.CreateOrderRequest;
import com.saga.orderservice.dto.OrderItemDto;
import com.saga.orderservice.entity.Order;
import com.saga.orderservice.entity.OrderItem;
import com.saga.shared.OrderItemMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderMapper map = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "totalPrice", expression = "java(com.saga.orderservice.util.OrderTotalPriceCalculatorUtil.calculateTotalPrice(request.orderItems()))")
    Order createOrderRequestToOrder(CreateOrderRequest request);
    List<OrderItem> orderItemDtoToOrderItem(List<OrderItemDto> orderItems);
    List<OrderItemMessage> orderItemToOrderItemMessage(List<OrderItem> orderItems);

}
