package com.example.ecommerce.service;

import com.example.ecommerce.dao.ProductDAO;
import com.example.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

    public Product addProduct(Product product) {
        return productDAO.save(product);
    }

    public List<Product> getAllProducts(int pageCount, String searchKey) {
        Pageable pageable = PageRequest.of(pageCount, 8);

        if (searchKey.equals("")) {
            return (List<Product>) productDAO.findAll(pageable);
        } else {
            return (List<Product>) productDAO.findByProductNameContainingIgnoreCase(
                    searchKey, pageable
            );
        }
    }

    public void deleteProducts(Integer id) {
        productDAO.deleteById(id);
    }

    public Product getProductDetailsById(Integer id) {

        return productDAO.findById(id).get();
    }

    public List<Product> getProductDetails(boolean isSingleProduct, Integer id) {
        // Check if buy one product
        // If not then move to cart
        if (isSingleProduct) {
            //Buy single product
            List<Product> buyList = new ArrayList<>();
            Product product = productDAO.findById(id).get();
            buyList.add(product);
            return buyList;
        } else {

        }
        return new ArrayList<>();
    }
}
