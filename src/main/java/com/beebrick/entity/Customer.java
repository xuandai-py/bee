package com.beebrick.entity;


import com.beebrick.entity.Authority.AuthenticationProvider;
import com.beebrick.entity.Authority.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@Entity
@Table(name = "customers")
public class Customer implements UserDetails{

    /*@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="CustomerID", nullable = false, updatable = false)
    private Integer CustomerID;*/

    @Id
    @NotBlank(message = "Please enter username")
    @Column(name = "Username")
    private String username;

    @Size(min = 8, message = "Password at least 8 characters")
    @Column(name = "Password")
    private String password;

    /*@NotBlank(message = "Please enter fullname")*/
    @Column(name = "Fullname")
    private String fullname;

    /*@NotBlank(message = "Please enter address")*/
    @Column(name = "Address")
    private String address;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,63}$", message = "Please enter the correct email format")
    @Column(name = "Email")
    private String email;

    @Pattern(regexp="([0][0-9]{9})", message = "Please enter the correct phone number format")
    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Image")
    private String image;

    @Column(name = "Gender")
    private Integer gender;

    @NotNull(message = "Please choose birthday of fill full mm/dd/yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Birthday")
    private LocalDate birthday = LocalDate.of(2020, Month.JANUARY, 8);


    @Column(name = "CreatedDate", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "ModifiedDate")
    @UpdateTimestamp
    private LocalDateTime modifiedDate;



    @Enumerated(EnumType.STRING)
    @Column(name = "AuthProvider")
    private AuthenticationProvider authenticationProvider;

    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }

    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    private boolean enabled=true;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customers")
    private ShoppingCart shoppingCart;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customers")
    private List<CustomerShipping> customerShippingList;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customers")
    private List<CustomerPayment> customerPaymentList;

    @OneToMany(mappedBy = "customers")
    private List<Order> order;



    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }



    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }



    public List<CustomerShipping> getCustomerShippingList() {
        return customerShippingList;
    }

    public void setCustomerShippingList(List<CustomerShipping> customerShippingList) {
        this.customerShippingList = customerShippingList;
    }

    public List<CustomerPayment> getCustomerPaymentList() {
        return customerPaymentList;
    }

    public void setCustomerPaymentList(List<CustomerPayment> customerPaymentList) {
        this.customerPaymentList = customerPaymentList;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }



}
