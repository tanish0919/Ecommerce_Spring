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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileService fileService;

    @Value("${project.image.path}")
    private String path;

    @Override
    public ProductDTO addProduct(Long categoryId, ProductDTO productDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category","categoryId",categoryId));

        List<Product> products = category.getProduct();
        if(products.stream().anyMatch(product -> product.getProductName().equalsIgnoreCase(productDTO.getProductName()))){
            throw new APIException("Product with this name already exists in this category");
        }


        Product product = modelMapper.map(productDTO,Product.class);

        product.setImage("default.png");
        product.setCategory(category);
        double specialPrice = product.getPrice() - (product.getDiscount()*0.01 *product.getPrice());
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct,ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByAnyOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAnyOrder);
        Page<Product> pageProducts = productRepository.findAll(pageDetails);
        List<Product> products = pageProducts.getContent();

        if(products.isEmpty()){
            throw new APIException ("No products to show");
        }
        List<ProductDTO> productDTOS = products.stream()
                .map(product-> modelMapper.map(product,ProductDTO.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageNumber(pageProducts.getNumber());
        productResponse.setPageSize(pageProducts.getSize());
        productResponse.setTotalElements(pageProducts.getTotalElements());
        productResponse.setTotalPage(pageProducts.getTotalPages());
        productResponse.setLastPage(pageProducts.isLast());
        return productResponse;
    }

    @Override
    public ProductResponse getProductByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category","categoryId",categoryId));

        Sort sortByAnyOrder = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAnyOrder);
        Page<Product> pageProducts = productRepository.findByCategoryOrderByPriceAsc(pageDetails, category);
        List<Product> products = pageProducts.getContent();

        if(products.isEmpty()){
            throw new APIException ("No products to show");
        }
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageNumber(pageProducts.getNumber());
        productResponse.setPageSize(pageProducts.getSize());
        productResponse.setTotalElements(pageProducts.getTotalElements());
        productResponse.setTotalPage(pageProducts.getTotalPages());
        productResponse.setLastPage(pageProducts.isLast());
        return productResponse;
    }

    @Override
    public ProductResponse getProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAnyOrder = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAnyOrder);

        Page<Product> pageProducts = productRepository.findByProductNameLikeIgnoreCase("%"+ keyword + "%",pageDetails);
        List<Product> products = pageProducts.getContent();
        if(products.isEmpty()){
            throw new APIException ("No products to show");
        }
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageNumber(pageProducts.getNumber());
        productResponse.setPageSize(pageProducts.getSize());
        productResponse.setTotalElements(pageProducts.getTotalElements());
        productResponse.setTotalPage(pageProducts.getTotalPages());
        productResponse.setLastPage(pageProducts.isLast());
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("product","productId",productId));

        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setDiscount(productDTO.getDiscount());
        product.setPrice(productDTO.getPrice());
        product.setSpecialPrice(productDTO.getSpecialPrice());
        productRepository.save(product);

        return modelMapper.map(product,ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("product","productId",productId));
        productRepository.delete(product);
        return modelMapper.map(product,ProductDTO.class);
    }

    @Override
    public ProductDTO updateImage(Long productId, MultipartFile file) throws IOException {
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("product","productId",productId));


        String fileName = fileService.uploadImage(path,file);

        productFromDB.setImage(fileName);

        ProductDTO updatedProduct = modelMapper.map(productFromDB, ProductDTO.class);
        return updatedProduct;
    }


}
