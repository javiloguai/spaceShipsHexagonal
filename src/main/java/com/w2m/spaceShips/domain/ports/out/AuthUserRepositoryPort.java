package com.w2m.spaceShips.domain.ports.out;

import com.w2m.spaceShips.infrastructure.db.entities.AuthUserEntity;

import java.util.Optional;

/**
 * @author javiloguai
 */
public interface AuthUserRepositoryPort {

    Optional<AuthUserEntity> findByUsername(final String username);

    AuthUserEntity save(final AuthUserEntity user);
}
