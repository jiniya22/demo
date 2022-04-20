package me.jiniworld.demo.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.dto.request.UserRequest;
import me.jiniworld.demo.domain.dto.response.BaseResponse;
import me.jiniworld.demo.domain.dto.response.DataResponse;
import me.jiniworld.demo.domain.dto.response.data.UserData;
import me.jiniworld.demo.service.UserService;
import me.jiniworld.demo.util.MessageUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@ApiResponses({
		@ApiResponse(responseCode = "200", description = MessageUtils.SUCCESS),
		@ApiResponse(responseCode = "400", description = MessageUtils.FAIL,
				content = @Content(schema = @Schema(implementation = BaseResponse.class)))})
@Tag(name = "user", description = "사용자 API")
@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
@RestController
public class UserController {
	
	private final UserService userService;

	@Operation(summary = "사용자 전체 조회", description = "사용자 간략정보를 모두 조회합니다.")
	@GetMapping("")
	public DataResponse<List<UserData.UserSimple>> selectAll() {
		List<UserData.UserSimple> users = userService.selectAll();
		return new DataResponse<>(users);
	}

	@Operation(summary = "사용자 추가", description = "사용자을 추가합니다.")
	@PostMapping("")
	public BaseResponse insert(@RequestBody @Valid final UserRequest user) {
		userService.insert(user);
		return new BaseResponse();
	}

	@Operation(summary = "사용자 상세 조회", description = "사용자의 상세정보와 보유하고 있는 가게 정보를 조회합니다.")
	@GetMapping("/{id}")
	public DataResponse<UserData.User> select(@PathVariable("id") long id) {
		return new DataResponse<>(userService.select(id));
	}

	@Operation(summary = "사용자 수정", description = "사용자 정보를 전체 수정합니다.")
	@PutMapping("/{id}")
	public BaseResponse update(@PathVariable("id") long id, @RequestBody @Valid final UserRequest user) {
		userService.update(id, user);
		return new BaseResponse();
	}

	@Operation(summary = "사용자 수정(patch)", description = "사용자 정보를 일부 수정합니다.<br>설정하지 않은 값은 수정하지 않습니다.")
	@PatchMapping("/{id}")
	public BaseResponse partialUpdate(@PathVariable("id") long id, @RequestBody final UserRequest user) {
		userService.partialUpdate(id, user);
		return new BaseResponse();
	}

	@Operation(summary = "사용자 삭제", description = "사용자를 삭제합니다.")
	@DeleteMapping("/{id}")
	public BaseResponse delete(@PathVariable("id") long id) {
		userService.delete(id);
		return new BaseResponse();
	}
	
}
