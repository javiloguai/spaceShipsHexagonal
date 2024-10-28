package com.w2m.spaceShips.domain.ports.out;

import com.w2m.spaceShips.domain.enums.Equipment;
import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;

import java.util.List;

/**
 * @author javiloguai
 */
public interface SpaceShipEquipmentRepositoryPort {

    List<SpaceShipEquipmentDomain> findAll();

    List<SpaceShipEquipmentDomain> findAllByShipEquipment(final Equipment shipEquipment);

    SpaceShipEquipmentDomain save(final SpaceShipEquipmentDomain equipmentDomain);

    void saveAll(final List<SpaceShipEquipmentDomain> equipment);

    void deleteAll();

    void deleteAllBySpaceShipId(final Long spaceShipId);

}
