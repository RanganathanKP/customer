package com.spares.customer.DTO;

public class RatingDTO {

    private Integer productID;
    private Float rating=0.0f;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
