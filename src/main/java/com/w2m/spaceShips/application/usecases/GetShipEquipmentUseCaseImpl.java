package com.w2m.spaceShips.application.usecases;

import com.w2m.spaceShips.domain.enums.Equipment;
import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;
import com.w2m.spaceShips.domain.ports.in.GetShipEquipmentUseCase;
import com.w2m.spaceShips.domain.ports.out.SpaceShipEquipmentRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author javiloguai
 */
@Component
public class GetShipEquipmentUseCaseImpl implements GetShipEquipmentUseCase {

    private final SpaceShipEquipmentRepositoryPort spaceShipEquipmentRepositoryPort;

    public GetShipEquipmentUseCaseImpl(final SpaceShipEquipmentRepositoryPort spaceShipEquipmentRepositoryPort) {
        this.spaceShipEquipmentRepositoryPort = spaceShipEquipmentRepositoryPort;
    }

    @Override
    public List<SpaceShipEquipmentDomain> findAllEquipment() {
        return spaceShipEquipmentRepositoryPort.findAll();
    }

    @Override
    public List<SpaceShipEquipmentDomain> findAllEquipmentEquipment(Equipment shipEquipment) {
        return spaceShipEquipmentRepositoryPort.findAllByShipEquipment(shipEquipment);
    }
}
