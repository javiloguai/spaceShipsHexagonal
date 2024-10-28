package com.w2m.spaceShips.domain.ports.in;

import com.w2m.spaceShips.domain.enums.Equipment;
import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;

import java.util.List;

/**
 * @author javiloguai
 */

public interface GetShipEquipmentUseCase {

    List<SpaceShipEquipmentDomain> findAllEquipment();

    List<SpaceShipEquipmentDomain> findAllEquipmentEquipment(final Equipment shipEquipment);

}
