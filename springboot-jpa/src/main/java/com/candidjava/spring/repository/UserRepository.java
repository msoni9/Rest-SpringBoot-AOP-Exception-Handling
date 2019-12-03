package com.candidjava.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.candidjava.spring.bean.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findById(long id);
	
}
