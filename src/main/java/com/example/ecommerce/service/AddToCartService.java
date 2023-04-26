package com.example.ecommerce.service;

import com.example.ecommerce.configuration.JwtRequestFilter;
import com.example.ecommerce.dao.AddToCartDAO;
import com.example.ecommerce.dao.ProductDAO;
import com.example.ecommerce.dao.UserDao;
import com.example.ecommerce.model.AddToCart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddToCartService {
    @Autowired
    private AddToCartDAO addToCartDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private UserDao userDao;

    public AddToCart addToCart(Integer id) {
        Product product = productDAO.findById(id).get();
        String username = JwtRequestFilter.CURRENT_USER;
        User user = null;
        if (username != null) {
            user = userDao.findById(username).get();
        }
        List<AddToCart> cartList = addToCartDAO.findByUser(user);
        List<AddToCart> filteredList = cartList.stream().filter(x -> x.getProduct().getId() == id).collect(Collectors.toList());

        if (filteredList.size() > 0) {
            return null;
        }
        if (product != null && user != null) {
            AddToCart cart = new AddToCart(product, user);
            return addToCartDAO.save(cart);
        }
        return null;
    }

    public List<AddToCart> getCartDetails() {
        String username = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(username).get();
        return addToCartDAO.findByUser(user);
    }

    public void deleteCartItem(Integer cartId) {
        addToCartDAO.deleteById(cartId);
    }
}
