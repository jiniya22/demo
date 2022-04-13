package me.jiniworld.demo.service;

import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.entity.Store;
import me.jiniworld.demo.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StoreService {
	
	private final StoreRepository storeRepository;

	public List<Store> select() {
		List<Store> stores = storeRepository.findAll();
//		stores.stream().forEach(store -> Optional.ofNullable(store.getUser()).map(User::getName));
		return stores;
	}

	public Store select(Long id) {
		Store store = storeRepository.findDistinctWithUserById(id).orElse(null);
		return store;
	}
	
}
