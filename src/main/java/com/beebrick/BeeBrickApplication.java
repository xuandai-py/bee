package com.beebrick;

import com.beebrick.entity.Authority.Role;
import com.beebrick.entity.Authority.UserRole;
import com.beebrick.entity.Customer;
import com.beebrick.service.CustomerService;
import com.beebrick.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BeeBrickApplication implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(BeeBrickApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer customer = new Customer();

		customer.setEmail("daibnxps10434@fpt.edu.vn");
		customer.setUsername("user");
		customer.setPassword(SecurityUtil.passwordEncoderBCry().encode("user"));

		customer.setAddress("HCM");

		customer.setFullname("kim");

		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(customer, role1));

		customerService.createCustomer(customer, userRoles);
	}
}
