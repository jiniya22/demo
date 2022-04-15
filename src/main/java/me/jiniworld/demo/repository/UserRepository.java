package me.jiniworld.demo.repository;


import me.jiniworld.demo.domain.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT DISTINCT u FROM User u join fetch u.stores")
//	@EntityGraph(attributePaths = "stores")
	List<User> findDistinctWithStoresBy();

	Optional<User> findByEmail(String email);

	//	@Query("SELECT DISTINCT u FROM User u join fetch u.stores WHERE u.id = ?1")
	@EntityGraph(attributePaths = "stores")
	Optional<User> findDistinctWithStoresById(Long id);
}
