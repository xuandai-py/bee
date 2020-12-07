package com.beebrick.controller;

import com.beebrick.entity.CartItem;
import com.beebrick.entity.Customer;
import com.beebrick.entity.Product;
import com.beebrick.entity.ShoppingCart;
import com.beebrick.service.CartItemService;
import com.beebrick.service.CustomerService;
import com.beebrick.service.ProductService;
import com.beebrick.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/cart")
    public String shoppingCart(Model model, Principal principal) {
        Customer customer = customerService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = customer.getShoppingCart();

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        shoppingCartService.updateShoppingCart(shoppingCart);

        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", shoppingCart);

        return "web/cart";
    }

    @RequestMapping("/addItem")
    public String addItem(
            @ModelAttribute("product") Product product,
            @ModelAttribute("qty") String qty,
            Model model, Principal principal) {

        Customer customer = customerService.findByUsername(principal.getName());

        product = productService.findByProductID(product.getProductID());


        if (Integer.parseInt(qty) > product.getQuantityInStock()) {
            model.addAttribute("notEnoughStock", true);
            return "forward:/product-detail?id="+product.getProductID();
        }

        CartItem cartItem = cartItemService.addProductToCartItem(product, customer, Integer.parseInt(qty));
        model.addAttribute("addSuccess", true);

        return "forward:/product-detail?id="+product.getProductID();
    }

    /* @RequestMapping("/updateCartItem")
    public String updateShoppingCart(
            @ModelAttribute("id") Long cartItemId,
            @ModelAttribute("qty") int qty
    ) {
        CartItem cartItem = cartItemService.findById(cartItemId);
        cartItem.setQty(qty);
        cartItemService.updateCartItem(cartItem);

        return "forward:/shoppingCart/cart";
    }*/

    @RequestMapping("/removeItem")
    public String removeItem(@RequestParam("id") Integer productID) {
        cartItemService.removeCartItem(cartItemService.findById(id));

        return "forward:/shoppingCart/cart";
    }
}
