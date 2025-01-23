package com.commerce.ecom.controller;

import com.commerce.ecom.config.AppConfig;
import com.commerce.ecom.config.AppConstant;
import com.commerce.ecom.model.Category;
import com.commerce.ecom.payload.CategoryDTO;
import com.commerce.ecom.payload.CategoryResponse;
import com.commerce.ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER , required = false) Integer pageNumber
            ,@RequestParam(name = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize
            ,@RequestParam(name = "sortBy", defaultValue = AppConstant.SORT_CATEGORIES_BY, required = false)String sortBy
            ,@RequestParam(name = "sortOrder", defaultValue = AppConstant.SORT_DIR,required = false)String sortOrder){
        CategoryResponse category = categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    @PostMapping("api/public/categories")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO category=  categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    @DeleteMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
        CategoryDTO status=  categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @PathVariable Long categoryId ,@RequestBody CategoryDTO category){
        CategoryDTO status = categoryService.updateCategory(categoryId,category);
        return new ResponseEntity<>(status,HttpStatus.OK);

    }

}
