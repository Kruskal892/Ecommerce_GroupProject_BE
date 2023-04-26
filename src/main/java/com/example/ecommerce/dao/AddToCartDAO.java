package com.example.ecommerce.dao;

import com.example.ecommerce.model.AddToCart;
import com.example.ecommerce.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddToCartDAO extends CrudRepository<AddToCart, Integer> {
    public List<AddToCart> findByUser(User user);
}
