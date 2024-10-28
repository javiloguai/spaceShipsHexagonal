package com.w2m.spaceShips.infrastructure.db.mappers;

import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;
import com.w2m.spaceShips.infrastructure.constants.MapperConstants;
import com.w2m.spaceShips.infrastructure.db.entities.SpaceShipEquipmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The Interface SpaceShipEquipmentDataBaseMapper.
 *
 * @author javiloguai
 */

@Mapper(componentModel = MapperConstants.COMPONENT_MODEL)
public interface SpaceShipEquipmentDataBaseMapper extends DatabaseMapper<SpaceShipEquipmentDomain, SpaceShipEquipmentEntity> {

    /**
     * Gets the mapper.
     *
     * @return the mapper
     */
    SpaceShipEquipmentDataBaseMapper INSTANCE = Mappers.getMapper(SpaceShipEquipmentDataBaseMapper.class);

}
