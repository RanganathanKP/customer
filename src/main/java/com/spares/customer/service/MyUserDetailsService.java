package com.spares.customer.service;

import com.spares.customer.entity.MyUserDetails;
import com.spares.customer.entity.UserEntity;
import com.spares.customer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String name) {
		Optional<UserEntity> user = userRepo.findByUserName(name);
		if(!user.isPresent()){
			throw new UsernameNotFoundException("Not found: " + name);
		}
		Optional<MyUserDetails> maps = user.map(MyUserDetails::new);
		if(maps.isPresent()){
			return maps.get();
		}else{
			throw new UsernameNotFoundException("Not found: " + name);
		}
	}

	
}