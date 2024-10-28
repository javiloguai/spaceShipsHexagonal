package com.w2m.spaceShips.application.usecases;

import com.w2m.spaceShips.domain.ports.in.DeleteSpaceShipUseCase;
import com.w2m.spaceShips.domain.ports.out.SpaceShipRepositoryPort;
import org.springframework.stereotype.Component;

/**
 * @author javiloguai
 */
@Component
public class DeleteSpaceShipUseCaseImpl implements DeleteSpaceShipUseCase {

    private final SpaceShipRepositoryPort spaceShipRepositoryPort;

    public DeleteSpaceShipUseCaseImpl(final SpaceShipRepositoryPort spaceShipRepositoryPort) {
        this.spaceShipRepositoryPort = spaceShipRepositoryPort;
    }

    @Override
    public void deleteSpaceShipById(final Long id) {
        spaceShipRepositoryPort.deleteById(id);
    }

    @Override
    public void deleteAllSpaceShips() {
        spaceShipRepositoryPort.deleteAll();

    }

}
