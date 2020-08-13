package com.spares.customer.controller;

import com.spares.customer.entity.RatingEntity;
import com.spares.customer.repository.RatingRepository;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.spares.customer.entity.OrderEntity;
import com.spares.customer.repository.OrderDetailRepository;
import com.spares.customer.repository.OrderRepository;
import com.spares.customer.repository.UserRepository;
import com.spares.customer.service.MyUserDetailsService;

import java.util.List;


@RestController
public class CustomerController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private MyUserDetailsService myuserDetailsService;

	@Autowired
	private RatingRepository ratingRepository;





//	product service must be called to get the data
////	@GetMapping("/customer/viewAllProduct")
////	@ResponseBody
////	public List<productEntity> findAllProduct(){
////		return productRepository.findAll();
////	}


	@PostMapping("/customer/createorder")
	@ResponseBody
	public OrderEntity saveproduct(@RequestBody OrderEntity order, @RequestHeader String Authorization){
		order.getOrderDetailEntityList().stream().forEach(orderdetail->{
			orderdetail.setOrderDetailStatus("Placed");
		});
		return orderRepository.save(order);
	}
	@GetMapping("/customer/getorder/{orderid}")
	@ResponseBody
	public OrderEntity getOrderByOrderID(@PathVariable int orderid ){
		return orderRepository.findById(orderid).get();
	}

	@PostMapping("/customer/rateorder")
	@ResponseBody
	public RatingEntity saverating(@RequestBody RatingEntity rating, @RequestHeader String Authorization){

		return ratingRepository.save(rating);
	}

	@GetMapping("/customer/getrating/{productid}")
	@ResponseBody
	public RatingEntity getRatingByProductID(@PathVariable int productid ){
		return ratingRepository.findById(productid).get();
	}

	@GetMapping("/customer/allRating")
	@ResponseBody
	public List<RatingEntity > getAllRating(){
		return ratingRepository.findAll();
	}

}
