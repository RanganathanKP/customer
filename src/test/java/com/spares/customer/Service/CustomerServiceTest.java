package com.spares.customer.Service;

import com.spares.customer.DTO.PurchaseOrderDTO;
import com.spares.customer.DTO.RatingDTO;
import com.spares.customer.DealerServiceProxy;
import com.spares.customer.configuration.SecurityConfigurer;
import com.spares.customer.entity.*;
import com.spares.customer.exception.CustomerException;
import com.spares.customer.repository.OrderDetailRepository;
import com.spares.customer.repository.OrderRepository;
import com.spares.customer.repository.RatingRepository;
import com.spares.customer.repository.UserRepository;
import com.spares.customer.service.CustomerService;
import com.spares.customer.service.MyUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {


    @Mock
    private DealerServiceProxy dealerServiceProxy;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepo;

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private OrderDetailRepository orderDetailRepository;

    @Mock
    private MyUserDetailsService myUserDetailsService;

    @Mock
    private SecurityConfigurer securityConfigurer;

    private CustomerService customerService;


    @Before
    public void init(){
        customerService= new CustomerService( dealerServiceProxy,  orderRepository,  userRepo,  ratingRepository,  orderDetailRepository);
    }

    @Test
    public void viewAllProductTest(){
        Integer userid=1;
        List<ProductEntity> prods=new ArrayList();
        ProductEntity prod= new ProductEntity();
        prod.setProductAmount(100);
        prod.setProductUserID(1);
        prod.setProductName("prod name");
        prod.setProductDescription("prod desc");
        prods.add(prod);
        when(dealerServiceProxy.findAllProduct()).thenReturn(prods);
        List<ProductEntity>response=new ArrayList();
        try{
            response=customerService.viewAllProduct(1);
        }catch(Exception e){ }
        assertEquals(1,response.get(0).getProductUserID());
    }

    @Test
    public void viewAllNegativeProductTest1(){
        Integer userid=1;
        List<ProductEntity>res=new ArrayList<>();
        List<ProductEntity> prods=new ArrayList();
        ProductEntity prod= new ProductEntity();
        prod.setProductAmount(100);
        prod.setProductUserID(1);
        prod.setProductName("prod name");
        prod.setProductDescription("prod desc");
        prods.add(prod);
        when(dealerServiceProxy.findAllProduct()).thenReturn(res);
        List<ProductEntity>response=new ArrayList();
        try{
            response=customerService.viewAllProduct(1);
        }catch(Exception e){
            assertEquals("Product not found",e.getMessage());
        }

    }
    @Test
    public void viewAllNegativeProductTest2(){
        Integer userid=1;
        List<ProductEntity> prods=getprods();
        when(dealerServiceProxy.findAllProduct()).thenReturn(prods);
        List<ProductEntity>response=new ArrayList();
        try{
            response=customerService.viewAllProduct(2);
        }catch(Exception e){
            assertEquals("No product for this user",e.getMessage());
        }

    }

    @Test
    public void createorderTest(){
        when(userRepo.findByUserName("Dealer")).thenReturn(getuser());
        ProductEntity prod = getprods().stream().findFirst().get();
        when(orderRepository.save(any())).thenReturn(getorder().stream().findFirst().get());
        when(dealerServiceProxy.findProductByID(1)).thenReturn(new ResponseEntity<>(prod, HttpStatus.OK));
        OrderEntity response = customerService.createorder(getpos(), "Basic RGVhbGVyOmRlYWxlcnBhc3N3b3Jk");
        assertEquals(200,response.getOrdertotaltmount());
    }
    @Test
    public void createorderfailTest(){
        OrderEntity response=new OrderEntity();
        when(userRepo.findByUserName("Dealer")).thenReturn(getuser());
        ProductEntity prod = getprods().stream().findFirst().get();
        prod.setProductId(null);
        when(orderRepository.save(any())).thenReturn(getorder().stream().findFirst().get());
        when(dealerServiceProxy.findProductByID(1)).thenReturn(new ResponseEntity<>(prod, HttpStatus.OK));
        try {
            response = customerService.createorder(getpos(), "Basic RGVhbGVyOmRlYWxlcnBhc3N3b3Jk");
        }catch(CustomerException e){
            assertEquals("Invalid Product ID 1",e.getMessage());
        }

    }

    @Test
    public void saveRatingTest(){
        List<RatingEntity> response=new ArrayList<>();
        when(userRepo.findByUserName("Dealer")).thenReturn(getuser());
        ProductEntity prod = getprods().stream().findFirst().get();
        when(ratingRepository.saveAll(any())).thenReturn(getratings());
        when(dealerServiceProxy.findProductByID(1)).thenReturn(new ResponseEntity<>(prod, HttpStatus.OK));
        response = customerService.saveRating(getratingdtos(), "Basic RGVhbGVyOmRlYWxlcnBhc3N3b3Jk");
        assertEquals(1,response.stream().findFirst().get()
                .getProductid());
    }

    @Test
    public void saveRatingnegativeTest(){
        List<RatingEntity> response=new ArrayList<>();
        when(userRepo.findByUserName("Dealer")).thenReturn(getuser());
        ProductEntity prod = getprods().stream().findFirst().get();
        prod.setProductId(null);
        when(ratingRepository.saveAll(any())).thenReturn(getratings());
        when(dealerServiceProxy.findProductByID(1)).thenReturn(new ResponseEntity<>(prod, HttpStatus.OK));
        try {
            response = customerService.saveRating(getratingdtos(), "Basic RGVhbGVyOmRlYWxlcnBhc3N3b3Jk");
        }catch(CustomerException e){
            assertEquals("Please Enter Valid Product ID:1",e.getMessage());
        }
    }

    @Test
    public void saveRatingnegativeTes2(){
        List<RatingEntity> response=new ArrayList<>();
        when(userRepo.findByUserName("Dealer")).thenReturn(getuser());
        ProductEntity prod = getprods().stream().findFirst().get();
        when(ratingRepository.saveAll(any())).thenReturn(getratings());
        when(dealerServiceProxy.findProductByID(1)).thenReturn(new ResponseEntity<>(prod, HttpStatus.OK));
        try {
            List<RatingDTO>requests= new ArrayList<>();
            RatingDTO request = getratingdtos().stream().findFirst().get();
            request.setRating(100.0f);
            requests.add(request);
            response = customerService.saveRating(requests, "Basic RGVhbGVyOmRlYWxlcnBhc3N3b3Jk");
        }catch(CustomerException e){
            assertEquals("Enter rating between 1-10.",e.getMessage());
        }
    }

    @Test
    public void viewOrderTest(){
        OrderEntity response=new OrderEntity();
        when(userRepo.findByUserName("Dealer")).thenReturn(getuser());
        ProductEntity prod = getprods().stream().findFirst().get();
        Optional<OrderEntity> responseorder = getorder().stream().findFirst();
        when(orderRepository.findById(any())).thenReturn(responseorder);
        response = customerService.viewOrder(1, "Basic RGVhbGVyOmRlYWxlcnBhc3N3b3Jk");
        assertEquals(1,response.getUserid());
    }

    @Test
    public void viewOrdernrgativeTest(){
        OrderEntity response=new OrderEntity();
        UserEntity userresponse = getuser().get();
        userresponse.setUserRole("Customer");
        Optional<UserEntity> userres = Optional.of(userresponse);
        when(userRepo.findByUserName("Dealer")).thenReturn(userres);
        ProductEntity prod = getprods().stream().findFirst().get();
        Optional<OrderEntity> responseorder = getorder().stream().findFirst();
        responseorder.get().setUserid(2);
        when(orderRepository.findById(any())).thenReturn(responseorder);
        try{
        response = customerService.viewOrder(1, "Basic RGVhbGVyOmRlYWxlcnBhc3N3b3Jk");
         }catch(CustomerException e){
        assertEquals("Order does not belong to user.",e.getMessage());
         }
    }
    @Test
    public void viewOrdernrgativeTest2(){
        OrderEntity response=new OrderEntity();
        UserEntity userresponse = getuser().get();
        userresponse.setUserRole("Customer");
        when(userRepo.findByUserName("Dealer")).thenReturn(Optional.of(userresponse));
        ProductEntity prod = getprods().stream().findFirst().get();
        Optional<OrderEntity> responseorder = Optional.of(new OrderEntity());
        when(orderRepository.findById(any())).thenReturn(responseorder);
        try{
            response = customerService.viewOrder(1, "Basic RGVhbGVyOmRlYWxlcnBhc3N3b3Jk");
        }catch(CustomerException e){
            assertEquals("Please Enter Valid Order ID.",e.getMessage());
        }
    }

    @Test
    public void getorderdetailTest(){

        Optional<OrderDetailEntity> responseorder = getdetails().stream().findFirst();
        when(orderDetailRepository.findById(any())).thenReturn(responseorder);
        OrderDetailEntity response = customerService.getorderdetail(1);
        assertEquals(1,response.getOrderdetailId());
    }

    @Test
    public void getorderdetailnegativeTest(){

        Optional<OrderDetailEntity> responseorder = getdetails().stream().findFirst();
        Optional<OrderDetailEntity> res=new ArrayList<OrderDetailEntity>().stream().findFirst();
        when(orderDetailRepository.findById(any())).thenReturn(res);
        try {
            OrderDetailEntity response = customerService.getorderdetail(1);
        }catch(CustomerException e){
            assertEquals("Order detail not found.",e.getMessage());
        }
    }

    @Test
    public void viewallRatingTest(){

        List<RatingEntity> responseorder = getratings();
        when(ratingRepository.findAll()).thenReturn(responseorder);
        List<RatingDTO> response = customerService.viewallRating();
        assertEquals(1,response.stream().findFirst().get().getProductID());
    }

    @Test
    public void updateOrderStatus(){
        OrderDetailEntity orderDetailEntity = getdetails().get(0);
        when(orderDetailRepository.save(orderDetailEntity)).thenReturn(orderDetailEntity);
        OrderDetailEntity response = customerService.updateOrderStatus(orderDetailEntity);
        assertEquals(1,response.getProductID());
        }


    @Test
    public void getusersTest(){
        List<UserEntity> list=new ArrayList();
        UserEntity orderDetailEntity = getuser().get();
        list.add(orderDetailEntity);
        when(userRepo.findAll()).thenReturn(list);
        List<UserEntity> response = customerService.getusers();
        assertEquals(1,response.get(0).getUserId());
    }

    public UserEntity saveUser(UserEntity user) {
        String pass = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(pass);
        return userRepo.save(user);
    }

    @Test
    public void saveUserTest(){
        UserEntity userEntity = getuser().get();
        when(userRepo.save(any())).thenReturn(userEntity);
       UserEntity response = customerService.saveUser(userEntity);
        assertEquals(1,response.getUserId());
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
