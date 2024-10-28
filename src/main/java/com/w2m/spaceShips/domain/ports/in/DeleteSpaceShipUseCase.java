package com.w2m.spaceShips.domain.ports.in;

/**
 * @author javiloguai
 */

public interface DeleteSpaceShipUseCase {

    void deleteSpaceShipById(final Long id);

    void deleteAllSpaceShips();

}
