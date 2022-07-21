package me.jiniworld.demo.service;

import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.dto.request.UserRequest;
import me.jiniworld.demo.domain.dto.response.data.UserData;
import me.jiniworld.demo.domain.entity.User;
import me.jiniworld.demo.exception.ResourceNotFoundException;
import me.jiniworld.demo.repository.UserRepository;
import me.jiniworld.demo.util.DateTimeUtils;
import me.jiniworld.demo.exception.InvalidInputException;
import me.jiniworld.demo.util.MessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	public List<UserData.UserSimple> selectAll() {
		return userRepository.findAll()
				.stream().map(UserData.UserSimple::new).collect(Collectors.toList());
	}

	public UserData.User select(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageUtils.INVALID_USER_ID));
		return new UserData.User(user);
	}

	@Transactional
	public void insert(final UserRequest u) {
		if(userRepository.findByEmail(u.getEmail()).isPresent()) {
			throw new InvalidInputException(MessageUtils.DUPLICATE_USER_EMAIL);
		}
		userRepository.save(User.builder()
				.birthDate(LocalDate.parse(u.getBirthDate(), DateTimeUtils.DTF_yyyyMMdd)).email(u.getEmail())
				.name(u.getName()).password(u.getPassword()).type(u.getType())
				.phoneNumber(u.getPhoneNumber()).sex(u.getSex()).build());
	}

	@Transactional
	public void update(long id, final UserRequest u) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new InvalidInputException(MessageUtils.INVALID_USER_ID));
		user.setBirthDate(LocalDate.parse(u.getBirthDate(), DateTimeUtils.DTF_yyyyMMdd));
		user.setEmail(u.getEmail());
		user.setName(u.getName());
		user.setPassword(u.getPassword());
		user.setPhoneNumber(u.getPhoneNumber());
		user.setSex(u.getSex());
		user.setType(u.getType());
		userRepository.save(user);
	}

	@Transactional
	public void partialUpdate(long id, final UserRequest u) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new InvalidInputException(MessageUtils.INVALID_USER_ID));
		if(StringUtils.isNotBlank(u.getBirthDate()))
			user.setBirthDate(LocalDate.parse(u.getBirthDate(), DateTimeUtils.DTF_yyyyMMdd));
		if(StringUtils.isNotBlank(u.getEmail())) user.setEmail(u.getEmail());
		if(StringUtils.isNotBlank(u.getName())) user.setName(u.getName());
		if(StringUtils.isNotBlank(u.getPassword())) user.setPassword(u.getPassword());
		if(StringUtils.isNotBlank(u.getPhoneNumber())) user.setPhoneNumber(u.getPhoneNumber());
		if(StringUtils.isNotBlank(u.getSex())) user.setSex(u.getSex());
		if(StringUtils.isNotBlank(u.getType())) user.setType(u.getType());
		userRepository.save(user);
	}

	@Transactional
	public void delete(long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new InvalidInputException(MessageUtils.INVALID_USER_ID));
		userRepository.delete(user);
	}
}
