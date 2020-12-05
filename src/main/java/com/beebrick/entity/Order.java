package com.beebrick.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderID")
	private Integer orderID;
	
	@Column(name = "OrderCode")
	private String orderCode;

	@Column(name = "CreatedDate")
	private LocalDateTime createdDate;
	
	@Column(name = "Status")
	private boolean status;

	/*@Column(name = "DeliveryFee")
	private double deliveryFee;
	
	@Column(name = "DeliveryAddress")
	private String deliveryAddress;
	
	@Column(name = "NameReceiver")
	private String nameReceiver;
	
	@Column(name = "PhoneReceiver")
	private String phoneReceiver;
	
	@Column(name = "Description")
	private String description;*/
	@OneToMany(mappedBy = "order", cascade= CascadeType.ALL)
	private List<CartItem> cartItemList;

	@OneToOne(cascade=CascadeType.ALL)
	private ShippingAddress shippingAddress;

	@OneToOne(cascade=CascadeType.ALL)
	private BillingAddress billingAddress;

	@ManyToOne
	@JoinColumn(name = "CustomerID")
	private Customer customers;
	
	@ManyToOne
	@JoinColumn(name = "PaymentID")
	private Payment payment;
	
	@OneToMany(mappedBy = "OrderID")
	Set<OrderDetail> orderDetail;

	/*@OneToMany(mappedBy = "orders")
	private List<Shipment> shipment;*/

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Customer getCustomer() {
		return customers;
	}

	public void setCustomer(Customer customer) {
		this.customers = customer;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Set<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
}
