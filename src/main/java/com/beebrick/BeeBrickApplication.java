package com.beebrick;

import com.beebrick.entity.Customer;
import com.beebrick.service.CustomerService;
import com.beebrick.util.SecuriryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeeBrickApplication implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(BeeBrickApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Customer customer = new Customer();

		customer.setEmail("kim@gmail.com");
		customer.setUsername("kim");
		customer.setPassword(SecuriryUtil.passwordEncoderBCry().encode("kim"));

		customer.setActive(true);
		customer.setAddress("HCM");
		customer.setEnabled(true);

		customer.setFullname("kim");
		customer.setPhone("01213456789");

		customerService.createCustomer(customer);*/
	}
}
