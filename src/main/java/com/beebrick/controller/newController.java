package com.beebrick.controller;


import com.beebrick.entity.Product;
import com.beebrick.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/web")
public class newController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String index(){
        return "web/view";
    }

    @GetMapping("/about-us")
    public String aboutUs(){
        return "web/about-us";
    }

    @GetMapping("/cart")
    public String cart(){
        return "web/cart";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        List<Product> list = productService.findAll();
        model.addAttribute("products", list);
        return "web/shop";
    }

}
