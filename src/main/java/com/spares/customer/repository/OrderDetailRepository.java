package com.spares.customer.repository;

import com.spares.customer.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.ws.rs.Produces;
import java.util.List;

@RepositoryRestResource
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer>{

    List<OrderDetailEntity > findByDealerID(Integer userid);


    List<OrderDetailEntity > findByOrderDetailStatusAndDealerID(String status,Integer userid);


}
