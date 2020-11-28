package com.beebrick.repository;

import com.beebrick.entity.CartItem;
import com.beebrick.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
}
