package com.example.ecommerce.service;

import com.example.ecommerce.configuration.JwtRequestFilter;
import com.example.ecommerce.dao.AddToCartDAO;
import com.example.ecommerce.dao.OrderDetailsDAO;
import com.example.ecommerce.dao.ProductDAO;
import com.example.ecommerce.dao.UserDao;
import com.example.ecommerce.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {
    private static final String ORDER_PLACE = "Placed";
    @Autowired
    private OrderDetailsDAO orderDetailsDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AddToCartDAO addToCartDAO;

    public List<OrderDetail> getOrder() {
        String currentUser =  JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser).get();

        return orderDetailsDAO.findByUser(user);
    }

    public void markedAsDelivered(Integer orderId) {
        OrderDetail orderDetail = orderDetailsDAO.findById(orderId).get();
        if (orderDetail != null) {
            orderDetail.setOrderStatus("Delivered");
            orderDetailsDAO.save(orderDetail);
        }
    }

    public List<OrderDetail> getAllOrder(String status) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        if (status.equals("All")) {
            orderDetailsDAO.findAll().forEach(x -> orderDetails.add(x));
        }else {
            orderDetailsDAO.findByOrderStatus(status).forEach(x-> orderDetails.add(x));
        }
         return orderDetails;
    }

    public void placeOrder(OrderInput orderInput, boolean isCartCheckout) {
        List<CountProductQuantity> countProductQuantityList = orderInput.getCountProductQuantityList();

        for (CountProductQuantity o : countProductQuantityList) {
            Product product = productDAO.findById(o.getProductId()).get();

            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(currentUser).get();

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getEmail(),
                    ORDER_PLACE,
                    product.getProductDiscountPrice() * o.getQuantity(),
                    product,
                    user
            );

            if (!isCartCheckout) {
                List<AddToCart> carts = addToCartDAO.findByUser(user);
                carts.stream().forEach(x -> addToCartDAO.deleteById(x.getCartId()));
            }
            orderDetailsDAO.save(orderDetail);
        }
    }
}
