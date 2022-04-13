package me.jiniworld.demo.controller.api;

import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.entity.Store;
import me.jiniworld.demo.domain.entity.User;
import me.jiniworld.demo.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping(path = "/api/stores")
@RestController
public class StoreController {
	
	private final StoreService storeService;

	@GetMapping("")
	public Map<String, Object> selectAll() {
		Map<String, Object> response = new HashMap<>();

		List<Store> stores = storeService.select();
		response.put("result", "SUCCESS");
		response.put("stores", stores);

		return response;
	}

	@GetMapping("/{id}")
	public Map<String, Object> select(@PathVariable("id") long id) {
		Map<String, Object> response = new HashMap<>();

		Store store = storeService.select(id);
			if(store != null) {
			response.put("result", "SUCCESS");
			response.put("store", store);
		} else {
			response.put("result", "FAIL");
			response.put("reason", "일치하는 가게 정보가 없습니다. 사용자 id를 확인해주세요.");
		}

		return response;
	}
	
}
