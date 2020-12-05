package com.beebrick.repository;

import com.beebrick.entity.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT * FROM customers WHERE IsActive = 0", nativeQuery = true)
    public Page<Customer> getAllCustomer(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE customers SET IsActive = 1 WHERE UserID=?1", nativeQuery = true)
    void delete(Integer customerID);

    @Query(value = "SELECT * FROM customers WHERE Username LIKE %?1% and IsActive = 0", nativeQuery = true)
    public Page<Customer> searchUser(String username, Pageable pageable);

    @Query(value = "SELECT * FROM customers WHERE Username LIKE %?1% and IsActive = 0", nativeQuery = true)
    public List<Customer> search1(String pageable);

    Customer findByUsername(String username);

    Customer findByEmail(String email);
}
