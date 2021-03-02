package me.jiniworld.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.entity.User;
import me.jiniworld.demo.repository.UserRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;

	public Optional<User> selectById(Long id) {
		return userRepository.findById(id);
	}
	
}
