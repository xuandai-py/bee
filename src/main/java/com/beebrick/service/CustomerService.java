package com.beebrick.service;

import com.beebrick.entity.Authority.PasswordResetToken;
import com.beebrick.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CustomerService {
    void save(Customer customer);

    void delete(Integer customerID);

    Customer findBycustomername(String customerName);

    Customer findByEmail(String email);

    Optional<Customer> findById(Integer customerID);

    Page<Customer> findPaginated(int pageNo, int pageSize);

    Page<Customer> findPaginated1(String customername, int pageNo, int pageSize);

    List<Customer> findAll();

    Optional<Customer> findByUsername(String username);

    //-------------------------------------------------------------------------------
    PasswordResetToken getPasswordResetToken(final String token);
    void createPasswordResetTokenForUser(final Customer customer, final String token);

    public Customer createCustomer(Customer customer) throws Exception;
}
