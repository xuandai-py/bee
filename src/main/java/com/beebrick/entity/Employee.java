package com.beebrick.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.beebrick.entity.Authority.AuthenticationProvider;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "employees")
public class Employee {


	@Id
	@NotBlank(message = "Please enter username")
	@Column(name = "Username")
	private String username;

	@Size(min = 8, message = "Password at least 8 characters")
	@Column(name = "Password")
	private String password;

	@NotBlank(message = "Please enter fullname")
	@Column(name = "Fullname")
	private String fullname;

	@NotBlank(message = "Please enter address")
	@Column(name = "Address")
	private String address;

	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,63}$", message = "Please enter the correct email format")
	@Column(name = "Email")
	private String email;

	@Pattern(regexp="([0][0-9]{9})", message = "Please enter the correct phone number format")
	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@Column(name = "Image")
	private String image;

	@NotNull(message = "Please choose birthday of fill full mm/dd/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "Birthday")
	private LocalDate birthday;

	@Column(name = "Gender")
	private Integer gender;

	@Column(name = "IsActive")
	private boolean isActive;

	@Column(name = "CreatedDate", updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "ModifiedDate")
	@UpdateTimestamp
	private LocalDateTime modifiedDate;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}