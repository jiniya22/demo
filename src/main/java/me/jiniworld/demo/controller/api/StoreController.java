package me.jiniworld.demo.controller.api;

import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.dto.response.BaseResponse;
import me.jiniworld.demo.domain.dto.response.DataResponse;
import me.jiniworld.demo.domain.entity.Store;
import me.jiniworld.demo.service.StoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/api/stores")
@RestController
public class StoreController {
	
	private final StoreService storeService;

	@GetMapping("")
	public DataResponse<List<Store>> selectAll() {
		List<Store> stores = storeService.select();
		return new DataResponse<>(stores);
	}

	@GetMapping("/{id}")
	public BaseResponse select(@PathVariable("id") long id) {
		Store store = storeService.select(id);
		if(store != null) {
			return new DataResponse<>(store);
		}
		return new BaseResponse("일치하는 가게 정보가 없습니다. 가게 id를 확인해주세요.");
	}
}
