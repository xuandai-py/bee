package com.beebrick.repository;

import com.beebrick.entity.CartItem;
import com.beebrick.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    //List<CartItem> findByOrder(Order order);
}
