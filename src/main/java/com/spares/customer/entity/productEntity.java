package com.spares.customer.entity;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "product")
public class productEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer productId;
	private String productName;
	private String productDescription;
	private String productAmount;
	private String productDealername;
	@UpdateTimestamp
	private LocalDateTime productcreationtime;

	public productEntity(Integer productId, String productName, String productDescription, String productAmount, String productDealername, LocalDateTime productcreationtime) {
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productAmount = productAmount;
		this.productDealername = productDealername;
		this.productcreationtime=productcreationtime;
	}
	public productEntity() {
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

	public String getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}

	public String getProductDealername() {
		return productDealername;
	}

	public void setProductDealername(String productDealername) {
		this.productDealername = productDealername;
	}




	
}
