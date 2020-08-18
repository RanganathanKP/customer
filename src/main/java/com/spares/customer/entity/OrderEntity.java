package com.spares.customer.entity;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "spareorder")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer orderId;
	@Column(name="order_totalamount")
	private Integer ordertotaltmount;
    @Column(name="user_id")
	private Integer userid;

    @Column(name="order_creation_time")
    @UpdateTimestamp
	private LocalDateTime ordercreationtime;


	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="order_id")
	private List<OrderDetailEntity> orderDetailEntityList;

	public OrderEntity(Integer orderId, Integer ordertotaltmount, List<OrderDetailEntity> orderDetailEntityList, Integer userid, String productDealername, LocalDateTime ordercreationtime) {
		this.orderId = orderId;
		this.ordertotaltmount = ordertotaltmount;
		this.orderDetailEntityList = orderDetailEntityList;
		this.userid = userid;
		this.ordercreationtime = ordercreationtime;
	}
	public OrderEntity() {
		super();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrdertotaltmount() {
		return ordertotaltmount;
	}

	public void setOrdertotaltmount(Integer ordertotaltmount) {
		this.ordertotaltmount = ordertotaltmount;
	}

	public List<OrderDetailEntity> getOrderDetailEntityList() {
		return orderDetailEntityList;
	}

	public void setOrderDetailEntityList(List<OrderDetailEntity> orderDetailEntityList) {
		this.orderDetailEntityList = orderDetailEntityList;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	public LocalDateTime getOrdercreationtime() {
		return ordercreationtime;
	}

	public void setOrdercreationtime(LocalDateTime ordercreationtime) {
		this.ordercreationtime = ordercreationtime;
	}
}
