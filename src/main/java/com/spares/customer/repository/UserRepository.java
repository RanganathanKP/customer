package com.spares.customer.repository;

import com.spares.customer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	
	Optional<UserEntity> findByUserName(String userName);


}
