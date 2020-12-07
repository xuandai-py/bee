package com.beebrick.controller;

import com.beebrick.entity.Customer;
import com.beebrick.repository.CustomerRepository;
import com.beebrick.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "admin/customer")
    public String index(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "admin/customer/index";
    }

    @RequestMapping(value = "admin/customer/add")
    public String add(Model model) {
        model.addAttribute("customer", new Customer());
        return "admin/customer/add";
    }

    @RequestMapping(value = "admin/customer/save", method = RequestMethod.POST)
    public String save(@Valid Customer customer, BindingResult bindingResult,
                       @RequestParam("photo_file") MultipartFile photo, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/customer/add";
        } else {
            if (customerService.findByUsername(customer.getUsername()) != null) {
                customer.setUsername(null);
                model.addAttribute("message", "Username is already exists!");
                return "admin/customer/add";
            } else {
                try {
                    Path path = Paths.get("uploads/");
                    InputStream inputStream = photo.getInputStream();
                    Files.copy(inputStream, path.resolve(photo.getOriginalFilename()),
                            StandardCopyOption.REPLACE_EXISTING);
                    customer.setImage(photo.getOriginalFilename().toLowerCase());
                } catch (Exception e) {
                }
                customerService.save(customer);
            }
            return "redirect:/admin/customer";
        }
    }

    @RequestMapping(value = "admin/customer/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("username") String username, Model model) {
        Optional<Customer> edit = customerService.findByUserName(username);
        edit.ifPresent(customer -> model.addAttribute("customer", customer));
        return "admin/customer/edit";
    }

    @RequestMapping(value = "admin/customer/update", method = RequestMethod.POST)
    public String update(@Valid Customer customer, BindingResult bindingResult,
                         @RequestParam("photo_file") MultipartFile photo, Model model) {

        if (bindingResult.hasErrors()) {
            return "admin/customer/edit";
        } else {
            try {
                Path path = Paths.get("uploads/");
                InputStream inputStream = photo.getInputStream();
                Files.copy(inputStream, path.resolve(photo.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                customer.setImage(photo.getOriginalFilename().toLowerCase());
            } catch (Exception e) {
            }
            customerService.save(customer);
            return "redirect:/admin/customer";
        }
    }
}
