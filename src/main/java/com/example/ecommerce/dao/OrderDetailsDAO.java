package com.example.ecommerce.dao;

import com.example.ecommerce.model.OrderDetail;
import com.example.ecommerce.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailsDAO extends CrudRepository<OrderDetail, Integer> {
    public List<OrderDetail> findByUser(User user);

    public List<OrderDetail> findByOrderStatus(String status);
}
