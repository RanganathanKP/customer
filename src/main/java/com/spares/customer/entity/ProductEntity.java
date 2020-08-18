package com.spares.customer.entity;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "product")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Integer productId;
	private String productName;
	private String productDescription;
	private Integer productAmount;
	@UpdateTimestamp
	private LocalDateTime productcreationtime;
	private Integer productUserID;

	public ProductEntity(Integer productId, String productName, String productDescription, Integer productAmount, Integer productUserID, LocalDateTime productcreationtime) {
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productAmount = productAmount;
		this.productUserID = productUserID;
		this.productcreationtime=productcreationtime;
	}
	public ProductEntity() {
		super();
	}

	public LocalDateTime getProductcreationtime() {
		return productcreationtime;
	}

	public void setProductcreationtime(LocalDateTime productcreationtime) {
		this.productcreationtime = productcreationtime;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Integer getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}

	public Integer getProductUserID() {
		return productUserID;
	}

	public void setProductUserID(Integer productUserID) {
		this.productUserID = productUserID;
	}




	
}
