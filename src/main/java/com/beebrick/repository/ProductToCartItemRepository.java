package com.beebrick.repository;

import com.beebrick.entity.Product;
import com.beebrick.entity.ProductToCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductToCartItemRepository extends JpaRepository<ProductToCartItem, Integer> {
}
