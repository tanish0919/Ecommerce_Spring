package com.commerce.ecom.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String productName;
    private String description;
    private String image;
    private Integer quantity;
    private double price;
    private double specialPrice;
    private double discount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User user;

    public Product(Long id, String productName, String description, String image, Integer quantity, double price, double specialPrice, double discount, Category category, User user) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.specialPrice = specialPrice;
        this.discount = discount;
        this.category = category;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(double specialPrice) {
        this.specialPrice = specialPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", specialPrice=" + specialPrice +
                ", discount=" + discount +
                ", category=" + category +
                ", user=" + user +
                '}';
    }
}
