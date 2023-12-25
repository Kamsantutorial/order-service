package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping()
    public List<OrderDTO> findAll() {
        List<OrderDTO> list = orderService.findAll();
        return list;
    }

    @PostMapping("/create")
    public OrderDTO post(@RequestBody OrderDTO orderDTO) {
        orderService.create(orderDTO);
        return orderDTO;
    }


}
