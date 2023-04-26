package com.example.ecommerce.controller;

import com.example.ecommerce.model.AddToCart;
import com.example.ecommerce.service.AddToCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddToCartController {
    @Autowired
    private AddToCartService addToCartService;

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/addToCart/{id}"})
    public AddToCart addToCart(@PathVariable(name = "id") Integer id) {
        return addToCartService.addToCart(id);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getCartDetails"})
    public List<AddToCart> getCartDetails() {
        return addToCartService.getCartDetails();
    }

    @PreAuthorize("hasRole('User')")
    @DeleteMapping({"/deleteCartItem/{cartId}"})
    public void deleteItemInCart(@PathVariable(name = "cartId") Integer carId) {
        addToCartService.deleteCartItem(carId);
    }
}
