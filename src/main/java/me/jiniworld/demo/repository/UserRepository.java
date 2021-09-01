package me.jiniworld.demo.repository;


import java.util.Optional;

import org.springframework.data.repository.Repository;

import me.jiniworld.demo.domain.entity.User;

public interface UserRepository extends Repository<User, Long> {

	Optional<User> findById(Long id);
	User save(User user);
	Optional<User> findByEmail(String email);
	void delete(User user);
	
}
