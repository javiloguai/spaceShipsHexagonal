package com.w2m.spaceShips.domain.ports.in;

import com.w2m.spaceShips.domain.models.SpaceShipDomain;

/**
 * @author javiloguai
 */
public interface CreateSpaceShipUseCase {

    SpaceShipDomain createSpaceShip(final SpaceShipDomain spaceShipDomain);

}
