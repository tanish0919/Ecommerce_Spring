package com.commerce.ecom.service;

import com.commerce.ecom.exceptions.APIException;
import com.commerce.ecom.exceptions.ResourceNotFoundException;
import com.commerce.ecom.model.Category;
import com.commerce.ecom.payload.CategoryDTO;
import com.commerce.ecom.payload.CategoryResponse;
import com.commerce.ecom.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        //implementation of sorting
        Sort sortByAnyOrder = sortOrder.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        //implementation of pagination
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAnyOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();
        if(categories.isEmpty()){
            throw new APIException("No category created till now!");
        }

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category,CategoryDTO.class)).toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setLastPage(categoryPage.isLast());
        categoryResponse.setTotalPage(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);

        // Ensure this is treated as a NEW entity
        category.setCategoryId(null);

        Category existing = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if (existing != null) {
            throw new APIException("Category with the Name: " + categoryDTO.getCategoryName() + " already exists!!!");
        }

        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }


    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category deleteCategory = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepository.delete(deleteCategory);
        CategoryDTO categoryDTO = modelMapper.map(deleteCategory,CategoryDTO.class);
        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO updatedContent) {

        Category updateCategory = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        updateCategory.setCategoryName(updatedContent.getCategoryName());
        categoryRepository.save(updateCategory);
        CategoryDTO categoryDTO = modelMapper.map(updateCategory,CategoryDTO.class);
        return categoryDTO;
    }
}
