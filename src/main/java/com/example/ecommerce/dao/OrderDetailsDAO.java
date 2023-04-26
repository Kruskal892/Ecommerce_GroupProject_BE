package com.example.ecommerce.dao;

import com.example.ecommerce.model.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailsDAO extends CrudRepository<OrderDetail, Integer> {

}
