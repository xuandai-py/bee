package com.beebrick.service;

import com.beebrick.entity.CartItem;
import com.beebrick.entity.ShoppingCart;

import java.util.List;

public interface CartItemService {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    CartItem updateCartItem(CartItem cartItem);
}
