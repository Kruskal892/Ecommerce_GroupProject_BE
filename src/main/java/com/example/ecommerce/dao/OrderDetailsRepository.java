package com.example.ecommerce.dao;

import org.springframework.data.repository.CrudRepository;
import com.example.ecommerce.model.OrderDetail;

public interface OrderDetailsRepository extends CrudRepository<OrderDetail, Integer> {

}
