package com.w2m.spaceShips.domain.ports.in;

import com.w2m.spaceShips.domain.models.SpaceShipDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author javiloguai
 */

public interface GetSpaceShipsUseCase {

    List<SpaceShipDomain> getAllSpaceShips();

    Page<SpaceShipDomain> pageAllSpaceShips(final Pageable pageable);

    Optional<SpaceShipDomain> findFirstByName(final String name);

    Page<SpaceShipDomain> pageAllSpaceShipsByName(final String name, final Pageable pageable);

    Optional<SpaceShipDomain> findSpaceShipById(final Long id);

    List<SpaceShipDomain> findSpaceShipsByIdList(final List<Long> spaceShipIds);

}
