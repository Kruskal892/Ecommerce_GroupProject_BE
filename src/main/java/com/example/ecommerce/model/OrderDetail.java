package com.example.ecommerce.model;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private String orderFullName;
    private String orderAddress;
    private String orderContactNumber;
    private String email;
    private String orderStatus;
    private Double orderQuantity;
    @OneToOne
    private Product product;
    @OneToOne
    private User user;
    public OrderDetail(String orderFullName, String orderAddress, String orderContactNumber, String email, String orderStatus, Double orderQuantity, Product product, User user) {
        this.orderFullName = orderFullName;
        this.orderAddress = orderAddress;
        this.orderContactNumber = orderContactNumber;
        this.email = email;
        this.orderStatus = orderStatus;
        this.orderQuantity = orderQuantity;
        this.product = product;
        this.user = user;
    }

    public OrderDetail() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderFullName() {
        return orderFullName;
    }

    public void setOrderFullName(String orderFullName) {
        this.orderFullName = orderFullName;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderContactNumber() {
        return orderContactNumber;
    }

    public void setOrderContactNumber(String orderContactNumber) {
        this.orderContactNumber = orderContactNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Double orderQuantity) {
        this.orderQuantity = orderQuantity;
    }


}
