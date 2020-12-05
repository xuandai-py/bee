package com.beebrick.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "manufactures")
public class Manufacturer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ManufacturerID")
	private Integer manufacturerID;

	@NotBlank(message = "Please enter manufacturer name")
	@Column(name = "ManufacturerName")
	private String manufacturerName;

	@NotBlank(message = "Please enter manufacturer address")
	@Column(name = "ManufacturerAddress")
	private String manufacturerAddress;

	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,63}$", message = "Please enter the correct email format")
	@Column(name = "ManufacturerEmail")
	private String manufacturerEmail;

	@Pattern(regexp="([0][0-9]{9})", message = "Please enter the correct phone number format")
	@Column(name = "ManufacturerPhone")
	private String manufacturerPhone;

	@Column(name = "Description", length = 2000)
	private String description;

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

	@OneToMany(mappedBy = "manufacturer")
	private List<Product> product;

	public Integer getManufacturerID() {
		return manufacturerID;
	}

	public void setManufacturerID(Integer manufacturerID) {
		this.manufacturerID = manufacturerID;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getManufacturerAddress() {
		return manufacturerAddress;
	}

	public void setManufacturerAddress(String manufacturerAddress) {
		this.manufacturerAddress = manufacturerAddress;
	}

	public String getManufacturerEmail() {
		return manufacturerEmail;
	}

	public void setManufacturerEmail(String manufacturerEmail) {
		this.manufacturerEmail = manufacturerEmail;
	}

	public String getManufacturerPhone() {
		return manufacturerPhone;
	}

	public void setManufacturerPhone(String manufacturerPhone) {
		this.manufacturerPhone = manufacturerPhone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}


}