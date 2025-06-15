package com.commerce.ecom.payload;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {
    List<ProductDTO> content;
    Integer pageNumber;
    Integer pageSize;
    Long totalElements;
    Integer totalPage;
    boolean lastPage;

    public ProductResponse(List<ProductDTO> content, Integer pageNumber, Integer pageSize, Long totalElements, Integer totalPage, boolean lastPage) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPage = totalPage;
        this.lastPage = lastPage;
    }

    public ProductResponse() {
    }

    public List<ProductDTO> getContent() {
        return content;
    }

    public void setContent(List<ProductDTO> content) {
        this.content = content;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
}
