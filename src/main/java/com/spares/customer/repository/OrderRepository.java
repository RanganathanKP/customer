package com.spares.customer.repository;

import com.spares.customer.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{


    OrderEntity findByOrderIdAndUserid(Integer id,Integer userid);
}
