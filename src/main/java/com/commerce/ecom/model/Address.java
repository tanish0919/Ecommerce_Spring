package com.commerce.ecom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @NotBlank
    @Size(min = 6 , message = "Street name is too small!")
    private String street;

    @NotBlank
    @Size(min = 4 , message = "City name is too small!")
    private String city;

    @NotBlank
    @Size(min = 6 , message = "State name is too small!")
    private String state;

    @NotBlank
    @Size(min = 6 , message = "Country name is too small!")
    private String country;

    @NotBlank
    @Size(min = 6 , message = "Zip code is too small!")
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

    public Address(String street, String city, String state, String country, String pincode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }

    public Address() {
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public @NotBlank @Size(min = 6, message = "Street name is too small!") String getStreet() {
        return street;
    }

    public void setStreet(@NotBlank @Size(min = 6, message = "Street name is too small!") String street) {
        this.street = street;
    }

    public @NotBlank @Size(min = 4, message = "City name is too small!") String getCity() {
        return city;
    }

    public void setCity(@NotBlank @Size(min = 4, message = "City name is too small!") String city) {
        this.city = city;
    }

    public @NotBlank @Size(min = 6, message = "State name is too small!") String getState() {
        return state;
    }

    public void setState(@NotBlank @Size(min = 6, message = "State name is too small!") String state) {
        this.state = state;
    }

    public @NotBlank @Size(min = 6, message = "Country name is too small!") String getCountry() {
        return country;
    }

    public void setCountry(@NotBlank @Size(min = 6, message = "Country name is too small!") String country) {
        this.country = country;
    }

    public @NotBlank @Size(min = 6, message = "Zip code is too small!") String getPincode() {
        return pincode;
    }

    public void setPincode(@NotBlank @Size(min = 6, message = "Zip code is too small!") String pincode) {
        this.pincode = pincode;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pincode='" + pincode + '\'' +
                ", users=" + users +
                '}';
    }
}
