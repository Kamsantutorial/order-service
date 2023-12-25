package com.example.orderservice.service.impl;


import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void create(OrderDTO orderDTO) {
        OrderEntity order = new OrderEntity();
        order.setCurrency(orderDTO.getCurrency());
        order.setDiscount(orderDTO.getDiscount());
        order.setPrice(orderDTO.getPrice());
        order.setUserId(orderDTO.getUserId());
        this.orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> findAll() {
        List<OrderDTO> list = new ArrayList<>();
        this.orderRepository.findAll().forEach( o-> {
            OrderDTO order = new OrderDTO();
            order.setCurrency(o.getCurrency());
            order.setDiscount(o.getDiscount());
            order.setPrice(o.getPrice());
            order.setUserId(o.getUserId());
            order.setId(o.getId());
            list.add(order);
        });
        return list;
    }

}
