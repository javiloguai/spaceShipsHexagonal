package com.w2m.spaceShips.infrastructure.db.repositories;

import com.w2m.spaceShips.infrastructure.db.entities.SpaceShipEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author javiloguai
 */
@Repository
public interface SpaceShipJPARepository extends JpaRepository<SpaceShipEntity, Long> {
    Optional<SpaceShipEntity> findById(final Long id);

    Page<SpaceShipEntity> findByNameContainingIgnoreCase(final String name, Pageable pageable);

    Optional<SpaceShipEntity> findFirstByNameIgnoreCase(final String name);

}