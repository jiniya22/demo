package me.jiniworld.demo.service;

import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.dto.response.data.StoreData;
import me.jiniworld.demo.domain.entity.Store;
import me.jiniworld.demo.exception.ResourceNotFoundException;
import me.jiniworld.demo.repository.StoreRepository;
import me.jiniworld.demo.exception.InvalidInputException;
import me.jiniworld.demo.util.MessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StoreService {
	
	private final StoreRepository storeRepository;

	public List<StoreData.StoreSimple> select() {
		List<Store> stores = storeRepository.findAll();
		return stores.stream().map(StoreData.StoreSimple::new).collect(Collectors.toList());
	}

	public StoreData.Store select(Long id) {
		Store store = storeRepository.findDistinctWithUserById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MessageUtils.INVALID_STORE_ID));
		return new StoreData.Store(store);
	}
	
}
