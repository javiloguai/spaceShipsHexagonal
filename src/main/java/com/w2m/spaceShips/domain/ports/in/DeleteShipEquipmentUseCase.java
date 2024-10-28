package com.w2m.spaceShips.domain.ports.in;

/**
 * @author javiloguai
 */

public interface DeleteShipEquipmentUseCase {

    void deleteAllBySpaceShipId(final Long spaceShipId);

    void deleteAll();
}
