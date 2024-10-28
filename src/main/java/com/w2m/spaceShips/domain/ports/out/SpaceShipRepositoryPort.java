package com.w2m.spaceShips.domain.ports.out;

import com.w2m.spaceShips.domain.enums.Equipment;
import com.w2m.spaceShips.domain.models.SpaceShipDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author javiloguai
 */
public interface SpaceShipRepositoryPort {
    List<SpaceShipDomain> findAll();

    Page<SpaceShipDomain> findAll(final Pageable pageable);

    Page<SpaceShipDomain> findAllByName(final String name, final Pageable pageable);

    Optional<SpaceShipDomain> findFirstByName(String name);

    Optional<SpaceShipDomain> findById(final Long id);

    List<SpaceShipDomain> findByIdList(final List<Long> shipsIds);

    SpaceShipDomain save(final SpaceShipDomain spaceShipDomain);

    Optional<SpaceShipDomain> addEquipment(final Long id, final Equipment shipEquipment);

    void deleteById(final Long id);

    void deleteAll();

}
