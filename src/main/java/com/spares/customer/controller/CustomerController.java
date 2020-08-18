package com.spares.customer.controller;

import com.netflix.discovery.converters.Auto;
import com.spares.customer.DTO.PurchaseOrderDTO;
import com.spares.customer.DTO.RatingDTO;
import com.spares.customer.DealerServiceProxy;
import com.spares.customer.entity.OrderDetailEntity;
import com.spares.customer.entity.ProductEntity;
import com.spares.customer.entity.RatingEntity;
import com.spares.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spares.customer.entity.OrderEntity;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;


	@GetMapping("/viewAllProducts")
	@ResponseBody
	public ResponseEntity<List<ProductEntity>> viewAllProduct(){
		System.out.println("inside view product");
			List<ProductEntity> productResponse= customerService.viewAllProduct();
			return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}

	@PostMapping("/createorder")
	@ResponseBody
	public ResponseEntity<OrderEntity> saveproduct(@RequestBody List<PurchaseOrderDTO> purchaseOrder, @RequestHeader String Authorization){
		System.out.println("inside createorder");
		OrderEntity createdOrder= customerService.createorder(purchaseOrder,Authorization);
		return new ResponseEntity<>(createdOrder, HttpStatus.OK);
	}

	@GetMapping("/getorder/{orderid}")
	@ResponseBody
	public ResponseEntity<OrderEntity> getOrderByOrderID(@PathVariable int orderid,@RequestHeader String Authorization ){
		OrderEntity responseOrder= customerService.viewOrder(orderid,Authorization);
		return new ResponseEntity<>(responseOrder, HttpStatus.OK);
	}

	@PostMapping("/rateorder")
	@ResponseBody
	public ResponseEntity<List<RatingEntity>> saveRating(@RequestBody List<RatingDTO> rating, @RequestHeader String Authorization){
		List<RatingEntity> response= customerService.saveRating(rating,Authorization);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getrating/{productid}")
	@ResponseBody
	public  ResponseEntity<RatingDTO> getRatingByProductID(@PathVariable int productid ){
		RatingDTO response= customerService.viewRating(productid);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/allRating")
	@ResponseBody
	public ResponseEntity<List<RatingDTO >> getAllRating(){
		List<RatingDTO> response= customerService.viewallRating();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/updatestatus/{orderdetailid}")
	@ResponseBody
	public  ResponseEntity<OrderDetailEntity>updateOrderStatus(@PathVariable Integer orderdetailid, @RequestHeader String Authorization){
		OrderDetailEntity response= customerService.updateOrderStatus(orderdetailid,Authorization);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getDealerOrder")
	@ResponseBody
	public  ResponseEntity<List<OrderDetailEntity>>getDealerOrder(@RequestHeader String Authorization){
		List<OrderDetailEntity >response= customerService.dealerAllOrder(Authorization);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getDealerPlacedOrder")
	@ResponseBody
	public  ResponseEntity<List<OrderDetailEntity>>getDealerPlacedOrder(@RequestHeader String Authorization){
		List<OrderDetailEntity> response= customerService.dealerPlacedOrder(Authorization);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
