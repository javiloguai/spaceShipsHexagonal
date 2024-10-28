package com.w2m.spaceShips.domain.ports.in;

import com.w2m.spaceShips.domain.enums.Equipment;
import com.w2m.spaceShips.domain.models.SpaceShipDomain;

import java.util.Optional;

/**
 * @author javiloguai
 */

public interface UpdateSpaceShipUseCase {
    Optional<SpaceShipDomain> addShipEquipment(final Long id, final Equipment shipEquipment);

    SpaceShipDomain updateSpaceShip(final SpaceShipDomain spaceShipDomain);

}
