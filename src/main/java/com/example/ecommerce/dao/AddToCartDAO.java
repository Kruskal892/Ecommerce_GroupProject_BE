package com.example.ecommerce.dao;

import com.example.ecommerce.model.AddToCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddToCartDAO extends CrudRepository<AddToCart, Integer> {
}
