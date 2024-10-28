package com.w2m.spaceShips.application.mappers;

import com.w2m.spaceShips.application.models.SpaceShipEquipmentDTO;
import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;
import com.w2m.spaceShips.infrastructure.constants.MapperConstants;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author javiloguai
 */
@Mapper(componentModel = MapperConstants.COMPONENT_MODEL)
public interface SpaceShipEquipmentDomainMapper extends DomainMapper<SpaceShipEquipmentDTO, SpaceShipEquipmentDomain> {

    SpaceShipEquipmentDomainMapper INSTANCE = Mappers.getMapper(SpaceShipEquipmentDomainMapper.class);

}
