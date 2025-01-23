package com.commerce.ecom.payload;

public class ProductDTO {
   private Long id;
   private String productName;
   private String image;
   private Integer quantity;
   private double price;
   private double discount;
   private double specialPrice;

   public ProductDTO(Long id, String productName, String image, Integer quantity, double price, double discount, double specialPrice) {
      this.id = id;
      this.productName = productName;
      this.image = image;
      this.quantity = quantity;
      this.price = price;
      this.discount = discount;
      this.specialPrice = specialPrice;
   }

   public ProductDTO() {
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

   public double getDiscount() {
      return discount;
   }

   public void setDiscount(double discount) {
      this.discount = discount;
   }

   public double getSpecialPrice() {
      return specialPrice;
   }

   public void setSpecialPrice(double specialPrice) {
      this.specialPrice = specialPrice;
   }
}
