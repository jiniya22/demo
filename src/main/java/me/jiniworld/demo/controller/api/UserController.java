package me.jiniworld.demo.controller.api;

import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.dto.request.UserRequest;
import me.jiniworld.demo.domain.dto.response.BaseResponse;
import me.jiniworld.demo.domain.dto.response.DataResponse;
import me.jiniworld.demo.domain.entity.User;
import me.jiniworld.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
@RestController
public class UserController {
	
	private final UserService userService;

	@GetMapping("")
	public DataResponse<List<User>> selectAll() {
		List<User> users = userService.selectAll();
		return new DataResponse<>(users);
	}

	@PostMapping("")
	public BaseResponse insert(@RequestBody @Valid final UserRequest user) {
		if(userService.insert(user)) {
			return new BaseResponse();
		}
		return new BaseResponse("이미 등록된 회원 정보입니다.");
	}

	@GetMapping("/{id}")
	public BaseResponse select(@PathVariable("id") long id) {
		User user = userService.select(id);
		if(user != null) {
			return new DataResponse<>(user);
		}
		return new BaseResponse("일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
	}

	@PutMapping("/{id}")
	public BaseResponse update(@PathVariable("id") long id, @RequestBody @Valid final UserRequest user) {
		if(userService.update(id, user) > 0) {
			return new BaseResponse();
		}
		return new BaseResponse("일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
	}
	
	@PatchMapping("/{id}")
	public BaseResponse partialUpdate(@PathVariable("id") long id, @RequestBody final UserRequest user) {
		if(userService.partialUpdate(id, user) > 0) {
			return new BaseResponse();
		}
		return new BaseResponse("일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
	}
	
	@DeleteMapping("/{id}")
	public BaseResponse delete(@PathVariable("id") long id) {
		if(userService.delete(id) > 0) {
			return new BaseResponse();
		}
		return new BaseResponse("일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
	}
	
}
