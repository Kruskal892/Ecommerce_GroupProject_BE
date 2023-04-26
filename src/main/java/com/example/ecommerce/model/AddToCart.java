package com.example.ecommerce.model;

import javax.persistence.*;

@Entity
@Table
public class AddToCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;
    @OneToOne
    private Product product;
    @OneToOne
    private User user;

    public AddToCart() {

    }

    public AddToCart(Product product, User user) {
        this.product = product;
        this.user = user;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
