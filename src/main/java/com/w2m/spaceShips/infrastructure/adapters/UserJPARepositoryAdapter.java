package com.w2m.spaceShips.infrastructure.adapters;

import com.w2m.spaceShips.domain.ports.out.AuthUserRepositoryPort;
import com.w2m.spaceShips.infrastructure.db.entities.AuthUserEntity;
import com.w2m.spaceShips.infrastructure.db.repositories.AuthUserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author javiloguai
 */
@Component
public class UserJPARepositoryAdapter implements AuthUserRepositoryPort {

    private final AuthUserJpaRepository userJpaRepository;

    public UserJPARepositoryAdapter(AuthUserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<AuthUserEntity> findByUsername(final String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public AuthUserEntity save(final AuthUserEntity user) {
        return userJpaRepository.save(user);
    }
}