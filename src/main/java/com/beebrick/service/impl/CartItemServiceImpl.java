package com.beebrick.service.impl;

import com.beebrick.entity.*;
import com.beebrick.repository.CartItemRepository;
import com.beebrick.repository.ProductToCartItemRepository;
import com.beebrick.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductToCartItemRepository productToCartItemRepository;

    @Override
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        BigDecimal bigDecimal = new BigDecimal(cartItem.getProduct().getPrice()).multiply(new BigDecimal(cartItem.getQty()));
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);

        cartItem.setSubtotal(bigDecimal);
        cartItemRepository.save(cartItem);

        return cartItem;
    }

    @Override
    public CartItem addProductToCartItem(Product product, Customer customer, int quantity) {
        List<CartItem> cartItemList = findByShoppingCart(customer.getShoppingCart());

        for(CartItem cartItem : cartItemList){
            if(product.getProductID() == cartItem.getProduct().getProductID()){
                cartItem.setQty(cartItem.getQty()+quantity);
                cartItem.setSubtotal(new BigDecimal(product.getPrice()).multiply(new BigDecimal(quantity)));
                cartItemRepository.save(cartItem);
                return cartItem;
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(customer.getShoppingCart());
        cartItem.setProduct(product);

        cartItem.setQty(quantity);
        cartItem.setSubtotal(new BigDecimal(product.getPrice()).multiply(new BigDecimal(quantity)));
        cartItem =cartItemRepository.save(cartItem);

        ProductToCartItem productToCartItem = new ProductToCartItem();
        productToCartItem.setProduct(product);
        productToCartItem.setCartItem(cartItem);
        productToCartItemRepository.save(productToCartItem);

        return cartItem;
    }
}
