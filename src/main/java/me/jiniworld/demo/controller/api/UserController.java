package me.jiniworld.demo.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.dto.request.UserRequest;
import me.jiniworld.demo.domain.entity.User;
import me.jiniworld.demo.service.UserService;

@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
@RestController
public class UserController {
	
	private final UserService userService;

	@GetMapping("")
	public Map<String, Object> selectAll() {
		Map<String, Object> response = new HashMap<>();

		List<User> users = userService.selectAll();
		response.put("result", "SUCCESS");
		response.put("user", users);

		return response;
	}

	@PostMapping("")
	public Map<String, Object> insert(@RequestBody @Valid final UserRequest user) {
		Map<String, Object> response = new HashMap<>();
		
		if(userService.insert(user)) {
			response.put("result", "SUCCESS");	
		} else {
			response.put("result", "FAIL");
			response.put("reason", "이미 등록된 회원 정보입니다.");
		}
		
		return response;
	}

	@GetMapping("/{id}")
	public Map<String, Object> select(@PathVariable("id") long id) {
		Map<String, Object> response = new HashMap<>();

		User user = userService.select(id);
		if(user != null) {
			response.put("result", "SUCCESS");
			response.put("user", user);
		} else {
			response.put("result", "FAIL");
			response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
		}

		return response;
	}
	
	@PutMapping("/{id}")
	public Map<String, Object> update(@PathVariable("id") long id, @RequestBody @Valid final UserRequest user) {
		Map<String, Object> response = new HashMap<>();

		int res = userService.update(id, user);
		if(res > 0) {
			response.put("result", "SUCCESS");
		} else {
			response.put("result", "FAIL");
			response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
		}

		return response;
	}
	
	@PatchMapping("/{id}")
	public Map<String, Object> partialUpdate(@PathVariable("id") long id, @RequestBody final UserRequest user) {
		Map<String, Object> response = new HashMap<>();

		int res = userService.partialUpdate(id, user);
		if(res > 0) {
			response.put("result", "SUCCESS");
		} else {
			response.put("result", "FAIL");
			response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
		}

		return response;
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Object> delete(@PathVariable("id") long id) {
		Map<String, Object> response = new HashMap<>();

		if(userService.delete(id) > 0) {
			response.put("result", "SUCCESS");
		} else {
			response.put("result", "FAIL");
			response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
		}

		return response;
	}
	
}
