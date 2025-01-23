package com.commerce.ecom.service;

import com.commerce.ecom.model.Product;
import com.commerce.ecom.payload.ProductDTO;
import com.commerce.ecom.payload.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts();

    ProductResponse getProductByCategory(Long categoryId);
}
