package com.commerce.ecom.service;

import com.commerce.ecom.exceptions.APIException;
import com.commerce.ecom.exceptions.ResourceNotFoundException;
import com.commerce.ecom.model.Category;
import com.commerce.ecom.model.Product;
import com.commerce.ecom.payload.ProductDTO;
import com.commerce.ecom.payload.ProductResponse;
import com.commerce.ecom.repository.CategoryRepository;
import com.commerce.ecom.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category","categoryId",categoryId));

        product.setImage("default.png");
        product.setCategory(category);
        double specialPrice = product.getPrice() - (product.getDiscount()*0.01 *product.getPrice());
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct,ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            throw new APIException ("No products to show");
        }
        List<ProductDTO> productDTOS = products.stream()
                .map(product-> modelMapper.map(product,ProductDTO.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse getProductByCategory(Long categoryId) {
        List<Product> products = productRepository.findBy
    }
}
