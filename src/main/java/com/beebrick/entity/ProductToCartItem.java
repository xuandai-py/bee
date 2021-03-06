package com.beebrick.entity;

import javax.persistence.*;

@Entity
@Table(name = "productToCartItem")
public class ProductToCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="ProductID")
    private Product product;

    @ManyToOne
    @JoinColumn(name="CartID")
    private CartItem cartItem;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }
}
