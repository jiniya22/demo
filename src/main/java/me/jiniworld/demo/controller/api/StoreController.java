package me.jiniworld.demo.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.dto.response.BaseResponse;
import me.jiniworld.demo.domain.dto.response.DataResponse;
import me.jiniworld.demo.domain.dto.response.data.StoreData;
import me.jiniworld.demo.service.StoreService;
import me.jiniworld.demo.util.MessageUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "store", description = "가게 API")
@RequiredArgsConstructor
@RequestMapping(path = "/api/stores")
@RestController
public class StoreController {
	
	private final StoreService storeService;

	@Operation(summary = "가게 간략정보 전체 조회", description = "가게의 간략정보를 모두 조회합니다.")
	@GetMapping("")
	public DataResponse<List<StoreData.StoreSimple>> selectAll() {
		List<StoreData.StoreSimple> stores = storeService.select();
		return new DataResponse<>(stores);
	}
	
	@Operation(summary = "가게 상세정보 조회", description = "가게의 상세정보와 해당 가게를 보유하고 있는 회원 정보를 조회합니다.",
			responses = {
				@ApiResponse(responseCode = "200", description = "가게 상세정보 조회 성공"),
				@ApiResponse(responseCode = "400", description = MessageUtils.INVALID_STORE_ID,
						content = @Content(schema = @Schema(implementation = BaseResponse.class))) })
	@GetMapping("/{id}")
	public DataResponse<StoreData.Store> select(
			@Parameter(name = "id", description = "가게 id", in = ParameterIn.PATH)
			@PathVariable("id") long id) {
		return new DataResponse<>(storeService.select(id));
	}
}
