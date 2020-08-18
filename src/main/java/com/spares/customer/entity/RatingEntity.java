package com.spares.customer.entity;

import javax.persistence.*;

@Entity(name = "rating")
public class RatingEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer ratingId;
	@Column(name="productid")
	private Integer productid;
	@Column(name="userid")
	private Integer userid;
	@Column(name="rating")
	private Integer rating;

	public RatingEntity(Integer ratingId, Integer productid, Integer userid, Integer rating) {
		this.ratingId = ratingId;
		this.productid = productid;
		this.userid = userid;
		this.rating = rating;
	}
	public RatingEntity() {
		super();
	}

	public Integer getRatingId() {
		return ratingId;
	}

	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
