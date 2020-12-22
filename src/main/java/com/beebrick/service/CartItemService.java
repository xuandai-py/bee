package com.beebrick.service;

import com.beebrick.entity.CartItem;
import com.beebrick.entity.Customer;
import com.beebrick.entity.Product;
import com.beebrick.entity.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface CartItemService {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    CartItem updateCartItem(CartItem cartItem);

    CartItem addProductToCartItem(Product product, Customer customer, int quantity);

    void removeCartItem(CartItem cartItem);

    Optional<CartItem> findById(Integer productID);
}
