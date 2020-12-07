package com.beebrick.entity.Authority;

import com.beebrick.entity.Customer;

import javax.persistence.*;

@Entity
@Table(name="user_role")
public class UserRole {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long userRoleId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private Customer customers;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Role role;
	
	public UserRole(){}

	public UserRole( Customer customers, Role role) {
		this.customers = customers;
		this.role = role;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}


	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Customer getCustomers() {
		return customers;
	}

	public void setCustomers(Customer customers) {
		this.customers = customers;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
