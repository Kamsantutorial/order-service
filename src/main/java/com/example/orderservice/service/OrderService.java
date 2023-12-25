package com.example.orderservice.service;


import com.example.orderservice.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    void create(OrderDTO orderDTO);
    List<OrderDTO> findAll();
}
