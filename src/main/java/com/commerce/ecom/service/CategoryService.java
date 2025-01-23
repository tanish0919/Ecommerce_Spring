package com.commerce.ecom.service;

import com.commerce.ecom.model.Category;
import com.commerce.ecom.payload.CategoryDTO;
import com.commerce.ecom.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize,String sortBy, String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(Long categoryId, CategoryDTO updatedContent);
}
