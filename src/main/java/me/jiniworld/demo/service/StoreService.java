package me.jiniworld.demo.service;

import lombok.RequiredArgsConstructor;
import me.jiniworld.demo.domain.entity.Store;
import me.jiniworld.demo.repository.StoreRepository;
import me.jiniworld.demo.util.InvalidInputException;
import me.jiniworld.demo.util.MessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StoreService {
	
	private final StoreRepository storeRepository;

	public List<Store> select() {
		List<Store> stores = storeRepository.findDistinctWithUserBy();
//		stores.stream().forEach(store -> Optional.ofNullable(store.getUser()).map(User::getName));
		return stores;
	}

		public Store select(Long id) {
		Store store = storeRepository.findDistinctWithUserById(id)
				.orElseThrow(() -> new InvalidInputException(MessageUtils.INVALID_STORE_ID));
		return store;
	}
	
}
