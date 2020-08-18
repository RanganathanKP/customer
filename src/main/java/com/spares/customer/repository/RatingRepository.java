package com.spares.customer.repository;

import com.spares.customer.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface RatingRepository extends JpaRepository<RatingEntity, Integer>{

	
	List<RatingEntity> findByProductid(Integer productid);
}
