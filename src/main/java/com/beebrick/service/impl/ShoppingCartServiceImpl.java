package com.beebrick.service.impl;

import com.beebrick.entity.CartItem;
import com.beebrick.entity.ShoppingCart;
import com.beebrick.repository.ShoppingCartRepository;
import com.beebrick.service.CartItemService;
import com.beebrick.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
        Double cartTotal = new Double(0);
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        for (CartItem cartItem : cartItemList){
            if(cartItem.getProduct().getQuantityInStock() > 0){
                cartItemService.updateCartItem(cartItem);
                cartTotal = cartItem.getSubtotal();
            }
        }

        shoppingCart.setGrandTotal(cartTotal);
        shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }
}
