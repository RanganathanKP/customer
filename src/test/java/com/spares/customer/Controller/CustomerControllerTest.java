package com.spares.customer.Controller;

import com.spares.customer.DTO.PurchaseOrderDTO;
import com.spares.customer.DTO.RatingDTO;
import com.spares.customer.controller.ControllerAdvisor;
import com.spares.customer.controller.CustomerController;
import com.spares.customer.entity.*;
import com.spares.customer.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private ControllerAdvisor controllerAdvisor;

    @InjectMocks
    private CustomerController customerController;

    
    @Test
    public void viewAllProductTest(){
        List<ProductEntity> list= getprods();
        ResponseEntity<List<ProductEntity>> response = new ResponseEntity<>(list, HttpStatus.OK);
        when(customerService.viewAllProduct(any())).thenReturn(list);
        ResponseEntity<List<ProductEntity>> serviceResponse = customerController.viewAllProduct(1);
        assertEquals(1,serviceResponse.getBody().get(0).getProductId());
    }

    @Test
    public void saveproductTest(){
        OrderEntity list= getorder().get(0);
        when(customerService.createorder(any(), any())).thenReturn(list);
        ResponseEntity<OrderEntity> serviceResponse = customerController.saveproduct(getpos(),"auth");
        assertEquals(1,serviceResponse.getBody().getUserid());
    }

    @Test
    public void saveRatingTest(){
        List<RatingEntity> list= getratings();
        when(customerService.saveRating(any(), any())).thenReturn(list);
        ResponseEntity<List<RatingEntity>>  serviceResponse = customerController.saveRating(getratingdtos(),"auth");
        assertEquals(1,serviceResponse.getBody().get(0).getProductid());
    }

    @Test
    public void getorderdetailTest(){
        List<OrderDetailEntity> list= getdetails();
        when(customerService.getorderdetail(any())).thenReturn(list.get(0));
        ResponseEntity< OrderDetailEntity>  serviceResponse = customerController.getorderdetail(1);
        assertEquals(1,serviceResponse.getBody().getProductID());
    }


    @Test
    public void updateOrderStatusTest(){
        List<OrderDetailEntity> list= getdetails();
        when(customerService.updateOrderStatus(any())).thenReturn(list.get(0));
        ResponseEntity< OrderDetailEntity>  serviceResponse = customerController.updateOrderStatus(list.get(0));
        assertEquals(1,serviceResponse.getBody().getProductID());
    }


    @Test
    public void getOrderByOrderIDTest(){

        when( customerService.viewOrder(any(),any())).thenReturn(getorder().get(0));
        ResponseEntity< OrderEntity>  serviceResponse = customerController.getOrderByOrderID(1,"auth");
        assertEquals(1,serviceResponse.getBody().getUserid());
    }

    @Test
    public void getAllRatingTest(){

        when( customerService.viewallRating()).thenReturn(getratingdtos());
        ResponseEntity< List<RatingDTO>>  serviceResponse = customerController.getAllRating();
        assertEquals(1,serviceResponse.getBody().get(0).getProductID());
    }

    @Test
    public void findAllUserTest(){
        List<UserEntity> userEntities=new ArrayList(0);
        userEntities.add(getuser().get());
        when( customerService.getusers()).thenReturn(userEntities);
        List<UserEntity>  serviceResponse = customerController.findAllUser();
        assertEquals(1,serviceResponse.get(0).getUserId());
    }

    @Test
    public void saveUserTest(){
        List<UserEntity> userEntities=new ArrayList(0);
        userEntities.add(getuser().get());
        when( customerService.saveUser(any())).thenReturn(getuser().get());
        UserEntity serviceResponse = customerController.saveUser(getuser().get());
        assertEquals(1,serviceResponse.getUserId());
    }


    public List<RatingDTO>getratingdtos(){
        RatingDTO rating= new RatingDTO();
        List<RatingDTO>ratings= new ArrayList();
        rating.setProductID(1);
        ratings.add(rating);
        return ratings;
    }

    public List<RatingEntity> getratings(){
        RatingEntity rating= new RatingEntity();
        List<RatingEntity>ratings= new ArrayList();
        rating.setProductid(1);
        rating.setRatingId(1);
        rating.setUserid(1);
        rating.setRating(1);
        ratings.add(rating);
        return ratings;
    }


    public List<PurchaseOrderDTO> getpos(){
        List<PurchaseOrderDTO> pos= new ArrayList();
        PurchaseOrderDTO po= new PurchaseOrderDTO();
        po.setProductid(1);
        po.setQuantity(1);
        pos.add(po);
        return pos;
    }

    public Optional<UserEntity> getuser(){
        UserEntity user= new UserEntity();
        user.setUserId(1);
        user.setUserName("user");
        user.setUserRole("dealer");
        user.setPassword("cGFzcw==");
        return Optional.of(user);
    }

    public List<OrderEntity> getorder(){
        List<OrderEntity> orders= new ArrayList();
        OrderEntity order = new OrderEntity();
        order.setOrderId(1);
        order.setUserid(1);
        order.setOrdertotaltmount(200);
        order.setOrderDetailEntityList(getdetails());
        orders.add(order);
        return orders;
    }

    public List<OrderDetailEntity>getdetails(){
        List<OrderDetailEntity> orders= new ArrayList();
        OrderDetailEntity order = new OrderDetailEntity();
        order.setDealerID(1);
        order.setOrderDetailStatus("PLACED");
        order.setUserID(1);
        order.setProductID(1);
        order.setOrderdetailId(1);
        order.setOrderDetailQuantity(1);
        order.setOrderID(1);
        orders.add(order);
        return orders;
    }

    public List<ProductEntity>getprods() {
        List<ProductEntity> prods = new ArrayList();
        ProductEntity prod = new ProductEntity();
        prod.setProductId(1);
        prod.setProductAmount(100);
        prod.setProductUserID(1);
        prod.setProductName("prod name");
        prod.setProductDescription("prod desc");
        prods.add(prod);
        return prods;
    }




    
}
