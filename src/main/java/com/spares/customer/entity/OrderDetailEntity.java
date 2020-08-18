package com.spares.customer.entity;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name = "orderdetail")
public class OrderDetailEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer orderdetailId;
    @Column(name="order_id")
	private Integer orderID;
    @Column(name="product_id")
	private Integer productID;
    @Column(name="orderdetail_quantity")
	private Integer orderDetailQuantity;
    @Column(name="orderdetail_status")
	private String orderDetailStatus;
    @Column(name="user_id")
	private Integer userID;
    @Column(name="dealer_id")
	private Integer dealerID;
    @Column(name="order_creation_time")
    @UpdateTimestamp
	private LocalDateTime orderDetailcreationtime;

	public OrderDetailEntity() {
		super();
	}

	public OrderDetailEntity(Integer orderdetailId, Integer orderID, Integer productID, Integer orderDetailQuantity, String orderDetailStatus, Integer userID, Integer dealerID, LocalDateTime orderDetailcreationtime) {
		this.orderdetailId = orderdetailId;
		this.orderID = orderID;
		this.productID = productID;
		this.orderDetailQuantity = orderDetailQuantity;
		this.orderDetailStatus = orderDetailStatus;
		this.userID = userID;
		this.dealerID = dealerID;
		this.orderDetailcreationtime = orderDetailcreationtime;
	}

	public Integer getOrderdetailId() {
		return orderdetailId;
	}

	public void setOrderdetailId(Integer orderdetailId) {
		this.orderdetailId = orderdetailId;
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public Integer getOrderDetailQuantity() {
		return orderDetailQuantity;
	}

	public void setOrderDetailQuantity(Integer orderDetailQuantity) {
		this.orderDetailQuantity = orderDetailQuantity;
	}

	public String getOrderDetailStatus() {
		return orderDetailStatus;
	}

	public void setOrderDetailStatus(String orderDetailStatus) {
		this.orderDetailStatus = orderDetailStatus;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getDealerID() {
		return dealerID;
	}

	public void setDealerID(Integer dealerID) {
		this.dealerID = dealerID;
	}

	public LocalDateTime getOrderDetailcreationtime() {
		return orderDetailcreationtime;
	}

	public void setOrderDetailcreationtime(LocalDateTime orderDetailcreationtime) {
		this.orderDetailcreationtime = orderDetailcreationtime;
	}
}
