package com.w2m.spaceShips.application.services.impl.impl;

import com.w2m.spaceShips.application.services.impl.AuthenticationApplicationService;
import com.w2m.spaceShips.application.services.impl.jwt.JwtService;
import com.w2m.spaceShips.domain.ports.out.AuthUserRepositoryPort;
import com.w2m.spaceShips.infrastructure.db.entities.AuthUserEntity;
import com.w2m.spaceShips.infrastructure.restapi.model.requests.AuthenticationRequest;
import com.w2m.spaceShips.infrastructure.restapi.model.requests.RegisterRequest;
import com.w2m.spaceShips.infrastructure.restapi.model.responses.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author javiloguai
 */
@Service
public class AuthenticationApplicationServiceImpl implements AuthenticationApplicationService {

    private final AuthUserRepositoryPort userRepositoryPort;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationApplicationServiceImpl(final AuthUserRepositoryPort userRepositoryPort,
                                                final PasswordEncoder passwordEncoder, final JwtService jwtService,
                                                final AuthenticationManager authenticationManager) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepositoryPort.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthenticationResponse.builder().token(token).build();

    }

    public AuthenticationResponse register(RegisterRequest request) {
        AuthUserEntity user = AuthUserEntity.builder().username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword())).role(request.getRole()).build();

        userRepositoryPort.save(user);

        return AuthenticationResponse.builder().token(jwtService.getToken(user)).build();

    }

}
