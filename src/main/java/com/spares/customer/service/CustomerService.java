package com.spares.customer.service;


import com.spares.customer.DTO.PurchaseOrderDTO;
import com.spares.customer.DTO.RatingDTO;
import com.spares.customer.DealerServiceProxy;
import com.spares.customer.entity.*;
import com.spares.customer.exception.CustomerException;
import com.spares.customer.repository.OrderDetailRepository;
import com.spares.customer.repository.OrderRepository;
import com.spares.customer.repository.RatingRepository;
import com.spares.customer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class CustomerService {

    @Autowired
    private DealerServiceProxy dealerServiceProxy;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;



    public List<ProductEntity> viewAllProduct(Integer userID) {
        List<ProductEntity> responseList=dealerServiceProxy.findAllProduct();
        if(CollectionUtils.isEmpty(responseList)||!Optional.ofNullable(responseList).isPresent()){
            throw new CustomerException("Product not found");
        }else{
            if(Optional.ofNullable(userID).isPresent()) {
                responseList = responseList.stream().filter(product -> product.getProductUserID() == userID).collect(Collectors.toList());
                if(CollectionUtils.isEmpty(responseList)){
                    throw new CustomerException("No product for this user");
                }
            }
        }
        return responseList;
    }

    public  OrderEntity createorder(List<PurchaseOrderDTO> purchaseOrder, String auth) {
        OrderEntity order= new OrderEntity();
        UserEntity user=getLoginedInUser(auth);
        List<Integer> amounts= new ArrayList<>();
        List<OrderDetailEntity> orderDetails=new ArrayList<>();
        purchaseOrder.forEach(purchase->{
            ResponseEntity<ProductEntity> productDetail=dealerServiceProxy.findProductByID(purchase.getProductid());
            if(!(Optional.ofNullable(productDetail.getBody()).isPresent()&&Optional.ofNullable(productDetail.getBody().getProductId()).isPresent())){
                throw new CustomerException("Invalid Product ID "+purchase.getProductid().toString());
            }
            amounts.add((productDetail.getBody().getProductAmount()*purchase.getQuantity()));
            OrderDetailEntity orderDetail= new OrderDetailEntity();
            orderDetail.setProductID(purchase.getProductid());
            orderDetail.setDealerID(productDetail.getBody().getProductUserID());
            orderDetail.setOrderDetailQuantity(purchase.getQuantity());
            orderDetail.setUserID(user.getUserId());
            orderDetail.setOrderDetailStatus("PLACED");
            orderDetails.add(orderDetail);
        });
        order.setUserid(user.getUserId());
        order.setOrderDetailEntityList(orderDetails);
        order.setOrdertotaltmount(amounts.stream()
                .reduce(0, (a, b) -> a + b));
        return orderRepository.save(order);
    }

    public List<RatingEntity> saveRating(List<RatingDTO> ratingDTO,String auth){
        UserEntity user=getLoginedInUser(auth);
        List<RatingEntity> ratingEntity= new ArrayList();
        ratingDTO.forEach(rating->{
            validateProduct(rating);
            RatingEntity ratingToSave= new RatingEntity();
            ratingToSave.setProductid(rating.getProductID());
            ratingToSave.setRating(Math.round(rating.getRating()));
            ratingToSave.setUserid(user.getUserId());
            ratingEntity.add(ratingToSave);
        });
        return ratingRepository.saveAll(ratingEntity);
    }

    public OrderEntity viewOrder(Integer orderID,String auth){
        UserEntity user=getLoginedInUser(auth);
        Optional<OrderEntity> responseorder= orderRepository.findById(orderID);
        if(responseorder.isPresent()){
             OrderEntity order=responseorder.get();
             if(order.getUserid()==user.getUserId()||!user.getUserRole().equalsIgnoreCase("Customer")){
                 return order;
             }else{
                 throw new CustomerException("Order does not belong to user.");
             }
        }else{
            throw new CustomerException("Please Enter Valid Order ID.");
        }
    }



    private void validateProduct(RatingDTO rating) {
        ResponseEntity<ProductEntity> productDetail= dealerServiceProxy.findProductByID(rating.getProductID());
        if(!(Optional.ofNullable(productDetail.getBody()).isPresent()&&Optional.ofNullable(productDetail.getBody().getProductId()).isPresent())){
                 throw new CustomerException("Please Enter Valid Product ID:"+rating.getProductID().toString());
              }
        if(rating.getRating()>10||rating.getRating()<0){
            throw new CustomerException("Enter rating between 1-10.");
        }
    }

//    public RatingDTO viewRating(Integer productid){
//        RatingDTO response=new RatingDTO();
//        float overallRating=0.0f;
//        List<Integer> ratingList= new ArrayList<>();
//       List<RatingEntity> ratings=ratingRepository.findByProductid(productid);
//       if(CollectionUtils.isEmpty(ratings)){
//           throw new CustomerException("Please Enter valid Product ID.");
//       }
//       ratings.forEach(rating->{
//           ratingList.add(rating.getRating());
//       });
//            overallRating=((float)(ratingList.stream().reduce(0, (a, b) -> a + b)))/(float)ratingList.size();
//            response.setProductID(productid);
//            response.setRating(overallRating);
//        return response;
//    }

    public OrderDetailEntity getorderdetail(Integer orderDetailID){
        return orderDetailRepository.findById(orderDetailID).get();
    }



    public List<RatingDTO> viewallRating(){
        List<RatingDTO> raitngDTOs= new ArrayList<>();
        List<RatingEntity> ratings=ratingRepository.findAll();
        Map<Integer, List<RatingEntity>> ratingMap = ratings.stream()
                .collect(groupingBy(RatingEntity::getProductid));
        ratingMap.entrySet().stream().forEach(set->{
            float overallRating=0.0f;
            RatingDTO ratingDTO = new RatingDTO();
            ratingDTO.setProductID(set.getKey());
            overallRating=((float)(set.getValue().stream().map(RatingEntity::getRating).collect(Collectors.toList()).stream().reduce(0, (a, b) -> a + b)))/(float)set.getValue().size();
            ratingDTO.setRating(overallRating);
            raitngDTOs.add(ratingDTO);
        });
       return raitngDTOs;
    }

    public OrderDetailEntity updateOrderStatus(OrderDetailEntity orderDetailEntity){
              return orderDetailRepository.save(orderDetailEntity);
    }

    public List<UserEntity> getusers(){
        return userRepo.findAll();
    }

    public UserEntity saveUser(UserEntity user){
        String pass = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(pass);
        return userRepo.save(user);
    }

    private UserEntity getLoginedInUser(String authorization) {
        String encodedPass = authorization.substring("Basic".length()).trim();
        byte[] actualByte = Base64.getDecoder().decode(encodedPass);
        String usenamePassword = new String(actualByte);
        String username = usenamePassword.substring(0, usenamePassword.indexOf(":"));
        String password = usenamePassword.substring(usenamePassword.indexOf(":") + 1);
        return userRepo.findByUserName(username).orElseThrow(()->new CustomerException("User Not Found."));
    }

}
