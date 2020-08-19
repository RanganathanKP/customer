package com.spares.customer;


import com.spares.customer.entity.ProductEntity;
import com.spares.customer.service.CustomerFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(name="dealer-client",url = "localhost:8083",fallback = CustomerFallBack.class)
public interface DealerServiceProxy {

    @GetMapping("/dealer/findAllProduct")
    @ResponseBody
    public List<ProductEntity> findAllProduct();

    @GetMapping("/dealer/findProduct/{productid}")
    @ResponseBody
    public ResponseEntity<ProductEntity> findProductByID(@PathVariable Integer productid);


}
