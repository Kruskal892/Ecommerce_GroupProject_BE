package com.example.ecommerce.controller;

import com.example.ecommerce.model.ImageModel;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = {"/addNewProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addProduct(@RequestPart("product") Product product,
                              @RequestPart("imageFile") MultipartFile[] file) {
        try {
            Set<ImageModel> images = uploadImage(file);
            product.setProductImg(images);
            return productService.addProduct(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();
        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels;
    }

    @GetMapping({"/getAllProducts"})
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int pageCount, @RequestParam(defaultValue = "") String searchKey) {
        List<Product> result = productService.getAllProducts(pageCount, searchKey);
        return result;
    }

    @GetMapping({"/getProductDetailsById/{id}"})
    public Product getProductDetailsById(@PathVariable("id") Integer id) {
        return productService.getProductDetailsById(id);
    }


    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping({"/deleteProducts/{id}"})
    public void deleteProducts(@PathVariable("id") Integer id) {
        productService.deleteProducts(id);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getProductDetails/{isSingleProduct}/{id}"})
    public List<Product> getProductDetails(@PathVariable(name = "isSingleProduct") boolean isSingleProduct,
                                           @PathVariable(name = "id") Integer id) {
        return productService.getProductDetails(isSingleProduct, id);
    }
}
