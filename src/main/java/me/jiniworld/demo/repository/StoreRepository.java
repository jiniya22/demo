package me.jiniworld.demo.repository;


import me.jiniworld.demo.domain.entity.Store;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @EntityGraph(attributePaths = "user")
    Optional<Store> findWithUserById(Long id);

}
