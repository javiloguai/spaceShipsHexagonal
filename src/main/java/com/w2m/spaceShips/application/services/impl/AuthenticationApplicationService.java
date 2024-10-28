package com.w2m.spaceShips.application.services.impl;

import com.w2m.spaceShips.infrastructure.restapi.model.requests.AuthenticationRequest;
import com.w2m.spaceShips.infrastructure.restapi.model.requests.RegisterRequest;
import com.w2m.spaceShips.infrastructure.restapi.model.responses.AuthenticationResponse;

/**
 * @author javiloguai
 */

public interface AuthenticationApplicationService {

    AuthenticationResponse login(AuthenticationRequest request);

    AuthenticationResponse register(RegisterRequest request);

}
