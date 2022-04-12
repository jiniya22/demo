package me.jiniworld.demo.service;

import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.entity.Store;
import me.jiniworld.demo.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StoreService {
	
	private final StoreRepository storeRepository;

	public Store select(Long id) {
		Store store = storeRepository.findWithUserById(id).orElse(null);
		return store;
	}
	
}
