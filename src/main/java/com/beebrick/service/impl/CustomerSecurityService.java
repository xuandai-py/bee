package com.beebrick.service.impl;

import com.beebrick.entity.Customer;
import com.beebrick.repository.CustomerRepository;
import com.beebrick.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerSecurityService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // load by customerName
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        //User user = userRepository.findByUsername(username);

        if(null == customer) {
            throw new UsernameNotFoundException("Username not found");
        }

        return customer;
    }

}