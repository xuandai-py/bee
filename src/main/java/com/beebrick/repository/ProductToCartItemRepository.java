package com.beebrick.repository;

import com.beebrick.entity.CartItem;
import com.beebrick.entity.Product;
import com.beebrick.entity.ProductToCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


@Transactional
public interface ProductToCartItemRepository extends JpaRepository<ProductToCartItem, Integer> {
    void deleteByCartItem(CartItem cartItem);
}
