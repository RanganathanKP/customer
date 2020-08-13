package com.spares.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spares.customer.entity.UserEntity;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	
	Optional<UserEntity> findByUserName(String userName);
}
