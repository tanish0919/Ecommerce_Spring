package com.commerce.ecom.repository;

import com.commerce.ecom.model.Category;
import com.commerce.ecom.payload.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String categoryName);
}
