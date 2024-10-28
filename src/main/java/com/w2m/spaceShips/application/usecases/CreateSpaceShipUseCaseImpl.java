package com.w2m.spaceShips.application.usecases;

import com.w2m.spaceShips.domain.models.SpaceShipDomain;
import com.w2m.spaceShips.domain.ports.in.CreateSpaceShipUseCase;
import com.w2m.spaceShips.domain.ports.out.SpaceShipRepositoryPort;
import org.springframework.stereotype.Component;

/**
 * @author javiloguai
 */
@Component
public class CreateSpaceShipUseCaseImpl implements CreateSpaceShipUseCase {

    private final SpaceShipRepositoryPort spaceShipRepositoryPort;

    public CreateSpaceShipUseCaseImpl(final SpaceShipRepositoryPort spaceShipRepositoryPort) {
        this.spaceShipRepositoryPort = spaceShipRepositoryPort;
    }

    @Override
    public SpaceShipDomain createSpaceShip(final SpaceShipDomain spaceShipDomain) {
        return spaceShipRepositoryPort.save(spaceShipDomain);
    }

}
