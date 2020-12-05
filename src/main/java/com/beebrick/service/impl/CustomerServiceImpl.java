package com.beebrick.service.impl;

import com.beebrick.entity.Authority.PasswordResetToken;
import com.beebrick.entity.Customer;
import com.beebrick.repository.CustomerRepository;
import com.beebrick.repository.PasswordResetTokenRepository;
import com.beebrick.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Integer customerID) {

    }

    @Override
    public Customer findBycustomername(String customerName) {
        return customerRepository.findByUsername(customerName);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> findById(Integer customerID) {
        return Optional.empty();
    }

    @Override
    public Page<Customer> findPaginated(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Page<Customer> findPaginated1(String customername, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(final Customer customer, final String token) {
        final PasswordResetToken passwordResetToken = new PasswordResetToken(token, customer);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public Customer createCustomer(Customer customer) throws Exception {
        Customer localCustomer = customerRepository.findByUsername(customer.getUsername());

        if(localCustomer != null){
            throw new Exception("user already exists. Nothing will be done");
        } else {
            localCustomer = customerRepository.save(customer);
        }
        return localCustomer;
    }
}
