package com.w2m.spaceShips.infrastructure.db.repositories;

import com.w2m.spaceShips.infrastructure.db.entities.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author javiloguai
 */
@Repository
public interface AuthUserJpaRepository extends JpaRepository<AuthUserEntity, Integer> {
    Optional<AuthUserEntity> findByUsername(String username);

}
