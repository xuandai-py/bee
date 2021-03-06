package com.beebrick.entity;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class CustomerShipping {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String userShippingName;
        private String userShippingStreet1;
        private String userShippingStreet2;
        private String userShippingCity;
        private String userShippingState;
        private String userShippingCountry;
        private String userShippingZipcode;
        private boolean userShippingDefault;

        @ManyToOne
        @JoinColumn(name = "CustomerID")
        private Customer customers;


        public Integer getId() {
            return id;
        }


        public void setId(Integer id) {
            this.id = id;
        }


        public String getUserShippingName() {
            return userShippingName;
        }


        public void setUserShippingName(String userShippingName) {
            this.userShippingName = userShippingName;
        }


        public String getUserShippingStreet1() {
            return userShippingStreet1;
        }


        public void setUserShippingStreet1(String userShippingStreet1) {
            this.userShippingStreet1 = userShippingStreet1;
        }


        public String getUserShippingStreet2() {
            return userShippingStreet2;
        }


        public void setUserShippingStreet2(String userShippingStreet2) {
            this.userShippingStreet2 = userShippingStreet2;
        }


        public String getUserShippingCity() {
            return userShippingCity;
        }


        public void setUserShippingCity(String userShippingCity) {
            this.userShippingCity = userShippingCity;
        }


        public String getUserShippingState() {
            return userShippingState;
        }


        public void setUserShippingState(String userShippingState) {
            this.userShippingState = userShippingState;
        }


        public String getUserShippingCountry() {
            return userShippingCountry;
        }


        public void setUserShippingCountry(String userShippingCountry) {
            this.userShippingCountry = userShippingCountry;
        }


        public String getUserShippingZipcode() {
            return userShippingZipcode;
        }


        public void setUserShippingZipcode(String userShippingZipcode) {
            this.userShippingZipcode = userShippingZipcode;
        }


    public Customer getCustomer() {
        return customers;
    }

    public void setCustomer(Customer customer) {
        this.customers = customer;
    }

    public boolean isUserShippingDefault() {
            return userShippingDefault;
        }


        public void setUserShippingDefault(boolean userShippingDefault) {
            this.userShippingDefault = userShippingDefault;
        }


    }

