package com.w2m.spaceShips.application.usecases;

import com.w2m.spaceShips.domain.ports.in.DeleteShipEquipmentUseCase;
import com.w2m.spaceShips.domain.ports.out.SpaceShipEquipmentRepositoryPort;
import org.springframework.stereotype.Component;

/**
 * @author javiloguai
 */
@Component
public class DeleteShipEquipmentUseCaseImpl implements DeleteShipEquipmentUseCase {

    private final SpaceShipEquipmentRepositoryPort spaceShipEquipmentRepositoryPort;

    public DeleteShipEquipmentUseCaseImpl(final SpaceShipEquipmentRepositoryPort spaceShipEquipmentRepositoryPort) {
        this.spaceShipEquipmentRepositoryPort = spaceShipEquipmentRepositoryPort;
    }

    @Override
    public void deleteAllBySpaceShipId(final Long spaceShipId) {
        spaceShipEquipmentRepositoryPort.deleteAllBySpaceShipId(spaceShipId);
    }

    @Override
    public void deleteAll() {
        spaceShipEquipmentRepositoryPort.deleteAll();
    }
}
