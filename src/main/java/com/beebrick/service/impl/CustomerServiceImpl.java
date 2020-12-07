package com.beebrick.service.impl;

import com.beebrick.entity.Authority.PasswordResetToken;
import com.beebrick.entity.Authority.UserRole;
import com.beebrick.entity.Customer;
import com.beebrick.entity.CustomerPayment;
import com.beebrick.entity.CustomerShipping;
import com.beebrick.entity.ShoppingCart;
import com.beebrick.repository.CustomerRepository;
import com.beebrick.repository.PasswordResetTokenRepository;
import com.beebrick.repository.RoleRepository;
import com.beebrick.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Integer customerID) {

    }


    @Override
    public Customer findByUsername(String customerName) {
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
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findByUserName(String username) {
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

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public Customer createCustomer(Customer customer, Set<UserRole> userRoleSet){
        Customer localCustomer = customerRepository.findByUsername(customer.getUsername());

        if(localCustomer != null){
             LOG.info("user already exists. Type another", customer.getUsername());
        } else {
            for(UserRole ur : userRoleSet){
                roleRepository.save(ur.getRole());
            }
            customer.getUserRoles().addAll(userRoleSet);

            // a new (null) shoppingCart when a user being created
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setCustomer(customer);
            customer.setShoppingCart(shoppingCart);
            customer.setCustomerShippingList(new ArrayList<CustomerShipping>());
            customer.setCustomerPaymentList(new ArrayList<CustomerPayment>());

            localCustomer = customerRepository.save(customer);
        }
        return localCustomer;
    }
}
