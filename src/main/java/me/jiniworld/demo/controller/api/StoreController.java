package me.jiniworld.demo.controller.api;

import lombok.RequiredArgsConstructor;
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
	public DataResponse<Store> select(@PathVariable("id") long id) {
		return new DataResponse<>(storeService.select(id));
	}
}
