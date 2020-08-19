package com.spares.customer.service;

import com.spares.customer.DealerServiceProxy;
import com.spares.customer.entity.ProductEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CustomerFallBack implements DealerServiceProxy {


    @Override
    public List<ProductEntity> findAllProduct() {
        return null;
    }

    @Override
    public ResponseEntity<ProductEntity> findProductByID(Integer productid) {
        return null;
    }
}
