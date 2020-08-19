package com.spares.customer.configuration;

import com.spares.customer.entity.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
              config.exposeIdsFor(ProductEntity.class, OrderDetailEntity.class, OrderEntity.class, RatingEntity.class,
                      UserEntity.class);
    }

}
