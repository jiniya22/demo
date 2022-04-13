package me.jiniworld.demo.repository;


import me.jiniworld.demo.domain.entity.Store;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @EntityGraph(attributePaths = "user", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Store> findDistinctWithUserById(Long id);

    @Query("SELECT DISTINCT s FROM Store s join fetch s.user")
    List<Store> findAll();
}
