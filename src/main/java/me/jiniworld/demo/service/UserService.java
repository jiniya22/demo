package me.jiniworld.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.dto.request.UserRequest;
import me.jiniworld.demo.domain.entity.User;
import me.jiniworld.demo.repository.UserRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;

	public Optional<User> select(Long id) {
		return userRepository.findById(id);
	}

	@Transactional
	public boolean insert(final UserRequest u) {
		if(userRepository.findByEmail(u.getEmail()).isPresent()) {
			return false;
		}
		userRepository.save(User.builder()
				.birthDate(u.getBirthDate()).email(u.getEmail())
				.name(u.getName()).password(u.getPassword())
				.phoneNumber(u.getPhoneNumber()).sex(u.getSex()).build());
		
		return true;
	}
	
}
