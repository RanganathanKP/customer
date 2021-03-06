package com.spares.customer.controller;

import com.spares.customer.DTO.PurchaseOrderDTO;
import com.spares.customer.DTO.RatingDTO;
import com.spares.customer.entity.*;
import com.spares.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/viewAllProducts")
    @ResponseBody
    public ResponseEntity<List<ProductEntity>> viewAllProduct(@RequestParam(value = "userID", required = false) Integer userid) {
        List<ProductEntity> productResponse = customerService.viewAllProduct(userid);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PostMapping("/createorder")
    @ResponseBody
    public ResponseEntity<OrderEntity> saveproduct(@RequestBody List<PurchaseOrderDTO> purchaseOrder, @RequestHeader String authorization) {
        OrderEntity createdOrder = customerService.createorder(purchaseOrder, authorization);
        return new ResponseEntity<>(createdOrder, HttpStatus.OK);
    }

    @PostMapping("/rateorder")
    @ResponseBody
    public ResponseEntity<List<RatingEntity>> saveRating(@RequestBody List<RatingDTO> rating, @RequestHeader String authorization) {
        List<RatingEntity> response = customerService.saveRating(rating, authorization);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //view order detail/status from dealer and admin (client)
    @GetMapping("/getOrderDetail/{orderDetailID}")
    @ResponseBody
    public ResponseEntity<OrderDetailEntity> getorderdetail(@PathVariable int orderDetailID) {
        OrderDetailEntity response = customerService.getorderdetail(orderDetailID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //update status from Dealer (client)
    @PostMapping("/updatestatus")
    @ResponseBody
    public ResponseEntity<OrderDetailEntity> updateOrderStatus(@RequestBody OrderDetailEntity orderDetailEntity) {
        OrderDetailEntity response = customerService.updateOrderStatus(orderDetailEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //View order that is created
    @GetMapping("/getorder/{orderid}")
    @ResponseBody
    public ResponseEntity<OrderEntity> getOrderByOrderID(@PathVariable Integer orderid, @RequestHeader String authorization) {
        OrderEntity responseOrder = customerService.viewOrder(orderid, authorization);
        return new ResponseEntity<>(responseOrder, HttpStatus.OK);
    }

    //view overall rating of product
    @GetMapping("/allRating")
    @ResponseBody
    public ResponseEntity<List<RatingDTO>> getAllRating() {
        List<RatingDTO> response = customerService.viewallRating();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    @ResponseBody
    public List<UserEntity> findAllUser() {
        return  customerService.getusers();
    }


    @PostMapping("/saveUser")
    @ResponseBody
    public UserEntity saveUser(@RequestBody UserEntity user) {
        return  customerService.saveUser(user);

    }


}
