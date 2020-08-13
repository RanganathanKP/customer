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
	private Integer orderid;
    @Column(name="product_id")
	private Integer productId;
    @Column(name="orderdetail_quantity")
	private String orderdetailquantity;
    @Column(name="orderdetail_status")
	private String orderDetailStatus;
    @Column(name="user_id")
	private String userid;

    @Column(name="order_creation_time")
    @UpdateTimestamp
	private LocalDateTime orderDetailcreationtime;

	public OrderDetailEntity(Integer orderdetailId, Integer orderid, Integer productId, String orderdetailquantity, String orderDetailStatus, String userid, LocalDateTime orderDetailcreationtime) {
		this.orderdetailId = orderdetailId;
		this.orderid = orderid;
		this.productId = productId;
		this.orderdetailquantity = orderdetailquantity;
		this.orderDetailStatus = orderDetailStatus;
		this.userid = userid;
		this.orderDetailcreationtime = orderDetailcreationtime;
	}
	public OrderDetailEntity() {
		super();
	}

	public Integer getOrderdetailId() {
		return orderdetailId;
	}

	public void setOrderdetailId(Integer orderdetailId) {
		this.orderdetailId = orderdetailId;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Integer getProductID() {
		return productId;
	}

	public void setProductID(Integer productId) {
		this.productId = productId;
	}

	public String getOrderdetailquantity() {
		return orderdetailquantity;
	}

	public void setOrderdetailquantity(String orderdetailquantity) {
		this.orderdetailquantity = orderdetailquantity;
	}

	public String getOrderDetailStatus() {
		return orderDetailStatus;
	}

	public void setOrderDetailStatus(String orderDetailStatus) {
		this.orderDetailStatus = orderDetailStatus;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public LocalDateTime getOrderDetailcreationtime() {
		return orderDetailcreationtime;
	}

	public void setOrderDetailcreationtime(LocalDateTime orderDetailcreationtime) {
		this.orderDetailcreationtime = orderDetailcreationtime;
	}
}
