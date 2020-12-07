package com.beebrick.controller.web;


import com.beebrick.entity.Authority.PasswordResetToken;
import com.beebrick.entity.Authority.Role;
import com.beebrick.entity.Authority.UserRole;
import com.beebrick.entity.Category;
import com.beebrick.entity.Customer;
import com.beebrick.entity.Product;
import com.beebrick.repository.CustomerRepository;
import com.beebrick.service.CategoryService;
import com.beebrick.service.CustomerService;
import com.beebrick.service.ProductService;
import com.beebrick.service.impl.CustomerSecurityService;
import com.beebrick.util.MailConstructor;
import com.beebrick.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.*;

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

    @Autowired
    private CustomerRepository customerRepository;


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

    /*@GetMapping("/product-detail")
    public String productdetail(@RequestParam("productID") Integer productID, Model model){
        List<Product> list = productService.getAll();
        model.addAttribute("products", list);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        List<Product> list1 = productService.getAllRandom();
        model.addAttribute("products1", list1);

        Optional<Product> edit = productService.findById(productID);
        edit.ifPresent(product -> model.addAttribute("product", product));

        List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);

        return "web/product-detail";
    }
*/
    @RequestMapping("/product-detail")
    public String productDetail(
            @PathParam("id") Integer productID, Model model, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            model.addAttribute("customer", customer);
        }

        Optional<Product> edit = productService.findById(productID);
        edit.ifPresent(product -> model.addAttribute("product", product));

        List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);

        return "web/product-detail";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("classActiveLogin", true); // enable popup tabs
        return "web/login-web";
    }


    @RequestMapping("/web-forgetPassword")
    public String forgetPassword(HttpServletRequest request,
                                 @ModelAttribute("recoverEmail") String customerEmail,
                                  Model model){

        model.addAttribute("classActiveForgetPassword", true); // enable popup tabs
        Customer customer = customerService.findByEmail(customerEmail);

        if(customer == null){
            model.addAttribute("emailNotExist", true);
            return "web/login-web";
        }

        String password = SecurityUtil.randomPassword();

        String encryptedPassword = SecurityUtil.passwordEncoderBCry().encode(password);
        customer.setPassword(encryptedPassword);

        customerService.save(customer);

        String token = UUID.randomUUID().toString();
        customerService.createPasswordResetTokenForUser(customer, token);

        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

        SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, customer, password);

        javaMailSender.send(newEmail);

        model.addAttribute("forgetPasswordEmailSent", "true");

        return "web/login-web";

    }

    @PostMapping(value = "/web-newUser")
    public String newCustomerPost(HttpServletRequest request,
                                  @ModelAttribute("email") String customerEmail,
                                  @ModelAttribute("username") String username,
                                  Model model) throws Exception {
        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("email", customerEmail);
        model.addAttribute("username", username);

        if(customerService.findByUsername(username) != null){
            model.addAttribute("usernameExists", true);
            return "web/login-web";
        }

        if(customerService.findByEmail(customerEmail) != null){
            model.addAttribute("emailExists", true);
            return "web/login-web";
        }

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setEmail(customerEmail);

        String password = SecurityUtil.randomPassword();

        String encryptedPassword = SecurityUtil.passwordEncoderBCry().encode(password);
        customer.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(customer, role));
        customerService.createCustomer(customer, userRoles);


        String token = UUID.randomUUID().toString();
        customerService.createPasswordResetTokenForUser(customer, token);

        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

        SimpleMailMessage email = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, customer, password);

        javaMailSender.send(email);

        model.addAttribute("emailSent", "true");
        //model.addAttribute("orderList", customer.getOrderList());

        return "web/login-web";

    }

    @RequestMapping("/web-newUser")
    public String newCustomer(Locale locale, @RequestParam("token") String token, Model model){
        PasswordResetToken passwordResetToken = customerService.getPasswordResetToken(token);

        if(passwordResetToken == null){
            String message = "Invalid token";
            model.addAttribute("message", message);
            return "/error";
        }

        Customer customer = passwordResetToken.getCustomer();
        String customername = customer.getUsername();

        UserDetails userDetails = customerSecurityService.loadUserByUsername(customername);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

       model.addAttribute("customer", customer);

        model.addAttribute("classActiveEdit", true);

        //model.addAttribute("classActiveNewUser", true); // enable popup tabs
        return "web/my-account";
    }

    @RequestMapping(value="/updateUserInfo", method=RequestMethod.POST)
    public String updateUserInfo(
            @ModelAttribute("customer") Customer customer,
            @ModelAttribute("newPassword") String newPassword,
            Model model
    ) throws Exception {
        Customer currentCustomer = customerService.findByUsername(customer.getUsername());

        if(currentCustomer == null) {
            throw new Exception ("User not found");
            // log
        }

        /*check email already exists*/
        if (customerService.findByEmail(customer.getEmail())!=null) {
            if(customerService.findByEmail(customer.getEmail()).getUsername() != currentCustomer.getUsername()) {
                model.addAttribute("emailExists", true);
                return "web/my-account";
            }
        }

        /*check username already exists*/
        if (customerService.findByUsername(customer.getUsername())!=null) {
            if(customerRepository.findByUsername(customer.getUsername()).getUsername() != currentCustomer.getUsername()) {
                model.addAttribute("usernameExists", true);
                return "web/my-account";
            }
        }

//		update password
        if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")){
            BCryptPasswordEncoder passwordEncoder = SecurityUtil.passwordEncoderBCry();
            String dbPassword = currentCustomer.getPassword();
            if(passwordEncoder.matches(customer.getPassword(), dbPassword)){
                currentCustomer.setPassword(passwordEncoder.encode(newPassword));
            } else {
                model.addAttribute("incorrectPassword", true);

                return "web/my-account";
            }
        }

        currentCustomer.setFullname(customer.getFullname());
        currentCustomer.setAddress(customer.getAddress());
        currentCustomer.setEmail(customer.getEmail());
        currentCustomer.setUsername(customer.getUsername());
        currentCustomer.setPhoneNumber(customer.getPhoneNumber());

        customerService.save(currentCustomer);

        model.addAttribute("updateSuccess", true);
        model.addAttribute("customer", currentCustomer);
        model.addAttribute("classActiveEdit", true);

        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("listOfCreditCards", true);

        UserDetails userDetails = customerSecurityService.loadUserByUsername(customer.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        //model.addAttribute("orderList", customer.getOrderList());

        return "web/my-account";
    }



    @RequestMapping("/my-account")
    public String myAcc(){
        return "web/my-account";
    }

}
