package com.commerce.ecom.repository;

import com.commerce.ecom.model.Category;
import com.commerce.ecom.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByCategoryOrderByPriceAsc(Pageable pageDetails, Category category);

    Page<Product> findByProductNameLikeIgnoreCase(String keyword, Pageable pageDetails);

    Product findByProductNameIgnoreCase(String productName);
}
