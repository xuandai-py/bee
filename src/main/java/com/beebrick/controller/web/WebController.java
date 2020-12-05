package com.beebrick.controller.web;


import com.beebrick.entity.Authority.PasswordResetToken;
import com.beebrick.entity.Category;
import com.beebrick.entity.Customer;
import com.beebrick.entity.Product;
import com.beebrick.service.CategoryService;
import com.beebrick.service.CustomerService;
import com.beebrick.service.ProductService;
import com.beebrick.service.impl.CustomerSecurityService;
import com.beebrick.util.MailConstructor;
import com.beebrick.util.SecuriryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerSecurityService customerSecurityService;



    @GetMapping(value = {"/", "/index"}) // tra ve trang index cho nguoi dung.
    public String index(){
        return "web/view";
    }

    @GetMapping("/about-us")
    public String aboutUs(){
        return "web/about-us";
    }

    @GetMapping("/blog")
    public String blog(){
        return "web/blog";
    }

    @GetMapping("/contact")
    public String contact(){
        return "web/contact";
    }


    @GetMapping("/cart")
    public String cart(){
        return "web/cart";
    }

    /** List category **/
    @GetMapping("/shop")
    public String shop(Model model) {
//		List<Product> list = productService.findAll();
//		model.addAttribute("products", list);
        List<Product> list = productService.getAll();
        model.addAttribute("products", list);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "web/shop";
    }

    @GetMapping("/productdetail")
    public String productdetail(@RequestParam("productID") Integer productID, Model model){
        List<Product> list = productService.getAll();
        model.addAttribute("products", list);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        List<Product> list1 = productService.getAllRandom();
        model.addAttribute("products1", list1);

        Optional<Product> edit = productService.findById(productID);
        edit.ifPresent(product -> model.addAttribute("product", product));
        return "web/product-detail";
    }


    @RequestMapping("/web-login")
    public String login(Model model){
        model.addAttribute("classActiveLogin", true); // enable popup tabs
        return "web/login-web";
    }

    @RequestMapping("/web-forgetPassword")
    public String forgetPassword(Model model){

        model.addAttribute("classActiveForgetPassword", true); // enable popup tabs
        return "web/my-account";
    }

    @PostMapping(value = "/web-newUser")
    public String newCustomerPost(HttpServletRequest request,
                                  @ModelAttribute("email") String customerEmail,
                                  @ModelAttribute("username") String username,
                                  Model model){
        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("email", customerEmail);
        model.addAttribute("username", username);

        if(customerService.findBycustomername(username) != null){
            model.addAttribute("usernameExists", true);
            return "web/view";
        }

        if(customerService.findByEmail(customerEmail) != null){
            model.addAttribute("emailExists", true);
            return "web/view";
        }

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setEmail(customerEmail);

        String password = SecuriryUtil.randomPassword();

        String encryptedPassword = SecuriryUtil.passwordEncoderBCry().encode(password);
        customer.setPassword(encryptedPassword);

        customerService.save(customer);

        String token = UUID.randomUUID().toString();
        customerService.createPasswordResetTokenForUser(customer, token);

        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

        SimpleMailMessage email = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, customer, password);

        javaMailSender.send(email);

        model.addAttribute("emailSent", "true");
        //model.addAttribute("orderList", customer.getOrderList());

        return "web/my-account";

    }

    @RequestMapping("/web-newUser")
    public String newCustomer(Locale locale, @RequestParam("token") String token, Model model){
        PasswordResetToken passwordResetToken = customerService.getPasswordResetToken(token);

        if(passwordResetToken == null){
            String message = "Invalid token";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        }

        Customer customer = passwordResetToken.getCustomer();
        String customername = customer.getUsername();

        UserDetails userDetails = customerSecurityService.loadUserByUsername(customername);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

       model.addAttribute("customer", customer);

        model.addAttribute("classActiveEdit", true);

        model.addAttribute("classActiveNewUser", true); // enable popup tabs
        return "web/my-account";
    }

    /*@RequestMapping(value="/newUser", method = RequestMethod.POST)
    public String newUserPost(
            HttpServletRequest request,
            @ModelAttribute("email") String userEmail,
            @ModelAttribute("username") String username,
            Model model
    ) throws Exception{
        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("email", userEmail);
        model.addAttribute("username", username);

        if (userService.findByUsername(username) != null) {
            model.addAttribute("usernameExists", true);

            return "myAccount";
        }

        if (userService.findByEmail(userEmail) != null) {
            model.addAttribute("emailExists", true);

            return "myAccount";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(userEmail);

        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

        SimpleMailMessage email = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);

        mailSender.send(email);

        model.addAttribute("emailSent", "true");
        model.addAttribute("orderList", user.getOrderList());

        return "myAccount";
    }


    @RequestMapping("/newUser")
    public String newUser(Locale locale, @RequestParam("token") String token, Model model) {
        PasswordResetToken passToken = userService.getPasswordResetToken(token);

        if (passToken == null) {
            String message = "Invalid Token.";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        }

        User user = passToken.getUser();
        String username = user.getUsername();

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("user", user);

        model.addAttribute("classActiveEdit", true);

        return "myProfile";
    }*/

    @RequestMapping("/my-account")
    public String myAcc(){
        return "web/my-account";
    }

}
