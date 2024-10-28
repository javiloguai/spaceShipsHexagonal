package com.w2m.spaceShips.application.usecases;

import com.w2m.spaceShips.domain.enums.Equipment;
import com.w2m.spaceShips.domain.models.SpaceShipDomain;
import com.w2m.spaceShips.domain.ports.in.UpdateSpaceShipUseCase;
import com.w2m.spaceShips.domain.ports.out.SpaceShipRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author javiloguai
 */
@Component
public class UpdateSpaceShipUseCaseImpl implements UpdateSpaceShipUseCase {

    private final SpaceShipRepositoryPort spaceShipRepositoryPort;

    public UpdateSpaceShipUseCaseImpl(final SpaceShipRepositoryPort spaceShipRepositoryPort) {
        this.spaceShipRepositoryPort = spaceShipRepositoryPort;
    }

    @Override
    public Optional<SpaceShipDomain> addShipEquipment(final Long id, final Equipment shipEquipment) {
        return spaceShipRepositoryPort.addEquipment(id, shipEquipment);
    }

    @Override
    public SpaceShipDomain updateSpaceShip(SpaceShipDomain spaceShipDomain) {
        return spaceShipRepositoryPort.save(spaceShipDomain);
    }

}
