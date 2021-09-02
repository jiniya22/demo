package me.jiniworld.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.dto.request.UserRequest;
import me.jiniworld.demo.domain.entity.User;
import me.jiniworld.demo.repository.UserRepository;
import me.jiniworld.demo.util.StringUtils;

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

	@Transactional
	public int update(long id, final UserRequest u) {
		Optional<User> oUser = userRepository.findById(id);
		if(!oUser.isPresent())
			return 0;
		
		User user = oUser.get();
		user.setBirthDate(u.getBirthDate());
		user.setEmail(u.getEmail());
		user.setName(u.getName());
		user.setPassword(u.getPassword());
		user.setPhoneNumber(u.getPhoneNumber());
		user.setSex(u.getSex());
		user.setType(u.getType());
		userRepository.save(user);
		return 1;
	}

	@Transactional
	public int partialUpdate(long id, final UserRequest u) {
		Optional<User> oUser = userRepository.findById(id);
		if(!oUser.isPresent())
			return 0;
		
		User user = oUser.get();
		if(StringUtils.isNotBlank(u.getBirthDate())) user.setBirthDate(u.getBirthDate());
		if(StringUtils.isNotBlank(u.getEmail())) user.setEmail(u.getEmail());
		if(StringUtils.isNotBlank(u.getName())) user.setName(u.getName());
		if(StringUtils.isNotBlank(u.getPassword())) user.setPassword(u.getPassword());
		if(StringUtils.isNotBlank(u.getPhoneNumber())) user.setPhoneNumber(u.getPhoneNumber());
		if(StringUtils.isNotBlank(u.getSex())) user.setSex(u.getSex());
		if(StringUtils.isNotBlank(u.getType())) user.setType(u.getType());
		userRepository.save(user);
		return 1;
	}
	
	@Transactional
	public int delete(long id) {
		Optional<User> oUser = userRepository.findById(id);
		if(oUser.isPresent()) {
			userRepository.delete(oUser.get());
			return 1;
		}
		return 0;
	}
	
}
