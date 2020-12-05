package com.beebrick.util;

import com.beebrick.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import org.thymeleaf.TemplateEngine;

import java.util.Locale;

@Component
public class MailConstructor {
    @Autowired
    private Environment env;

    @Autowired
    private TemplateEngine templateEngine;

    public SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, Customer customer, String password
    ) {

        String url = contextPath + "/newCustomer?token="+token;
        String message = "\nPlease click on this link to verify your email and edit your personal information. Your password is: \n"+password;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(customer.getEmail());
        email.setSubject("Beebrick toys - New member");
        email.setText(url+message);
        email.setFrom(env.getProperty("support.email"));
        return email;

    }
}
