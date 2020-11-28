package com.beebrick.repository;

import com.beebrick.entity.Product;
import com.beebrick.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}
