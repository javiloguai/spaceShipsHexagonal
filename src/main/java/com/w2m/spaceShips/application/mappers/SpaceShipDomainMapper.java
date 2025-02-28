package com.w2m.spaceShips.application.mappers;

import com.w2m.spaceShips.application.models.SpaceShipDTO;
import com.w2m.spaceShips.application.models.SpaceShipEquipmentDTO;
import com.w2m.spaceShips.domain.models.SpaceShipDomain;
import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;
import com.w2m.spaceShips.infrastructure.constants.MapperConstants;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author javiloguai
 */
@Mapper(componentModel = MapperConstants.COMPONENT_MODEL)
public interface SpaceShipDomainMapper extends DomainMapper<SpaceShipDTO, SpaceShipDomain> {

    SpaceShipDomainMapper INSTANCE = Mappers.getMapper(SpaceShipDomainMapper.class);

    default SpaceShipEquipmentDomain map(SpaceShipEquipmentDTO shipEquipmentDTO) {

        return SpaceShipEquipmentDomainMapper.INSTANCE.dtoToDomain(shipEquipmentDTO);
    }

    default List<SpaceShipEquipmentDomain> mapDtoList(List<SpaceShipEquipmentDTO> equipmentDTO) {
        return SpaceShipEquipmentDomainMapper.INSTANCE.dtoToDomain(equipmentDTO);
    }

    default SpaceShipEquipmentDTO map(SpaceShipEquipmentDomain shipEquipment) {

        return SpaceShipEquipmentDomainMapper.INSTANCE.domainToDto(shipEquipment);
    }

    default List<SpaceShipEquipmentDTO> mapList(List<SpaceShipEquipmentDomain> equipment) {
        return SpaceShipEquipmentDomainMapper.INSTANCE.domainToDto(equipment);
    }

}
