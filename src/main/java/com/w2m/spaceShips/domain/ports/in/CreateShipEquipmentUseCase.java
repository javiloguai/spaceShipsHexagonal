package com.w2m.spaceShips.domain.ports.in;

import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;

import java.util.List;

/**
 * @author javiloguai
 */

public interface CreateShipEquipmentUseCase {

    void assignAllEquipment(final List<SpaceShipEquipmentDomain> equipment);

    SpaceShipEquipmentDomain addShipEquipment(final SpaceShipEquipmentDomain shipEquipment);
}
