package com.beebrick.service.impl;

import com.beebrick.entity.CartItem;
import com.beebrick.entity.ShoppingCart;
import com.beebrick.repository.CartItemRepository;
import com.beebrick.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;


    @Override
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        //Double qtyCartItem = new Double(cartItem.getProduct().getPrice()).
        BigDecimal bigDecimal = new BigDecimal(cartItem.getProduct().getPrice()).multiply(new BigDecimal(cartItem.getQty()));
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);

        cartItem.setSubtotal(bigDecimal.doubleValue());
        cartItemRepository.save(cartItem);

        return cartItem;
    }
}
