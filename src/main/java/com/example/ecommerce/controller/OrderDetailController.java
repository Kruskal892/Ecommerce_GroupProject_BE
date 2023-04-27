package com.example.ecommerce.controller;

import com.example.ecommerce.model.OrderDetail;
import com.example.ecommerce.model.OrderInput;
import com.example.ecommerce.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;


    @PreAuthorize("hasRole('User')")
    @PostMapping({"/placeOrder/{isCartCheckout}"})
    public void placeOrder(@PathVariable(name = "isCartCheckout") boolean isCartCheckout,
                           @RequestBody OrderInput orderInput) {
        orderDetailService.placeOrder(orderInput, isCartCheckout);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getOrder"})
    public List<OrderDetail> getOrder() {
        return orderDetailService.getOrder();
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getAllOrder/{status}"})
    public List<OrderDetail> getAllOrder(@PathVariable(name = "status")String status) {
        return orderDetailService.getAllOrder(status);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/markedAsDelivered/{orderId}"})
    public void markedAsDelivered(@PathVariable(name = "orderId") Integer orderId) {
        orderDetailService.markedAsDelivered(orderId);
    }
    
}
