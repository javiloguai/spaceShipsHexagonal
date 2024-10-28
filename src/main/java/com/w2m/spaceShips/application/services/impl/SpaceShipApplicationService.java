package com.w2m.spaceShips.application.services.impl;

import com.w2m.spaceShips.application.models.SpaceShipDTO;
import com.w2m.spaceShips.domain.enums.Equipment;
import com.w2m.spaceShips.domain.models.SpaceShipDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author javiloguai
 * I put some examples of other kind of call I will not implement anything on other examples
 */

public interface SpaceShipApplicationService {

    List<SpaceShipDomain> getAllSpaceShips();

    Page<SpaceShipDomain> pageAllSpaceShips(final Pageable pageable);

    Page<SpaceShipDomain> pageAllSpaceShipsByName(final String name, final Pageable pageable);

    List<SpaceShipDomain> getAllSpaceShipsByEquipment(final Equipment shipEquipment);

    SpaceShipDomain findSpaceShipById(final Long id);

    SpaceShipDomain createSpaceShip(final SpaceShipDTO spaceShipDTO);

    SpaceShipDomain updateSpaceShip(final Long id, final SpaceShipDTO spaceShipDTO);

    SpaceShipDomain addEquipment(final Long id, final Equipment shipEquipment);

    void deleteSpaceShipById(final Long id);

    void deleteAllSpaceShips();

}
