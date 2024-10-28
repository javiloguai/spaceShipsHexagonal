package com.w2m.spaceShips.infrastructure.db.repositories;

import com.w2m.spaceShips.domain.enums.Equipment;
import com.w2m.spaceShips.infrastructure.db.entities.SpaceShipEquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author javiloguai
 */
@Repository
public interface SpaceShipEquipmentJPARepository extends JpaRepository<SpaceShipEquipmentEntity, Long> {

    List<SpaceShipEquipmentEntity> findAllBySpaceShipId(final Long spaceShipId);

    void deleteAllBySpaceShipId(final Long spaceShipId);

    List<SpaceShipEquipmentEntity> findByShipEquipment(final Equipment shipEquipment);

}