package me.jiniworld.demo.controller.api;

import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.dto.request.UserRequest;
import me.jiniworld.demo.domain.dto.response.BaseResponse;
import me.jiniworld.demo.domain.dto.response.DataResponse;
import me.jiniworld.demo.domain.dto.response.data.UserData;
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
	public DataResponse<List<UserData.UserSimple>> selectAll() {
		List<UserData.UserSimple> users = userService.selectAll();
		return new DataResponse<>(users);
	}

	@PostMapping("")
	public BaseResponse insert(@RequestBody @Valid final UserRequest user) {
		userService.insert(user);
		return new BaseResponse();
	}

	@GetMapping("/{id}")
	public DataResponse<UserData.User> select(@PathVariable("id") long id) {
		return new DataResponse<>(userService.select(id));
	}

	@PutMapping("/{id}")
	public BaseResponse update(@PathVariable("id") long id, @RequestBody @Valid final UserRequest user) {
		userService.update(id, user);
		return new BaseResponse();
	}
	
	@PatchMapping("/{id}")
	public BaseResponse partialUpdate(@PathVariable("id") long id, @RequestBody final UserRequest user) {
		userService.partialUpdate(id, user);
		return new BaseResponse();
	}
	
	@DeleteMapping("/{id}")
	public BaseResponse delete(@PathVariable("id") long id) {
		userService.delete(id);
		return new BaseResponse();
	}
	
}
