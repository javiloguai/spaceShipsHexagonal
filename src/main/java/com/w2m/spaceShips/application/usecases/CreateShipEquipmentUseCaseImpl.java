package com.w2m.spaceShips.application.usecases;

import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;
import com.w2m.spaceShips.domain.ports.in.CreateShipEquipmentUseCase;
import com.w2m.spaceShips.domain.ports.out.SpaceShipEquipmentRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author javiloguai
 */
@Component
public class CreateShipEquipmentUseCaseImpl implements CreateShipEquipmentUseCase {

    private final SpaceShipEquipmentRepositoryPort spaceShipEquipmentRepositoryPort;

    public CreateShipEquipmentUseCaseImpl(final SpaceShipEquipmentRepositoryPort spaceShipEquipmentRepositoryPort) {
        this.spaceShipEquipmentRepositoryPort = spaceShipEquipmentRepositoryPort;
    }

    @Override
    public void assignAllEquipment(final List<SpaceShipEquipmentDomain> equipment) {
        spaceShipEquipmentRepositoryPort.saveAll(equipment);
    }

    @Override
    public SpaceShipEquipmentDomain addShipEquipment(SpaceShipEquipmentDomain shipEquipment) {
        return spaceShipEquipmentRepositoryPort.save(shipEquipment);
    }
}
