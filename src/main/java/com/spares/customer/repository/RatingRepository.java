package com.spares.customer.repository;

import com.spares.customer.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Integer>{

	
	Optional<RatingEntity> findByProductid(Integer productid);
}
