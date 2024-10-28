package com.w2m.spaceShips.application.mappers;

import com.w2m.spaceShips.application.models.SpaceShipEquipmentDTO;
import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-28T01:49:38+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 21 (Eclipse Adoptium)"
)
@Component
public class SpaceShipEquipmentDomainMapperImpl implements SpaceShipEquipmentDomainMapper {

    @Override
    public SpaceShipEquipmentDomain dtoToDomain(SpaceShipEquipmentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SpaceShipEquipmentDomain.SpaceShipEquipmentDomainBuilder spaceShipEquipmentDomain = SpaceShipEquipmentDomain.builder();

        spaceShipEquipmentDomain.id( dto.getId() );
        spaceShipEquipmentDomain.spaceShipId( dto.getSpaceShipId() );
        spaceShipEquipmentDomain.shipEquipment( dto.getShipEquipment() );

        return spaceShipEquipmentDomain.build();
    }

    @Override
    public SpaceShipEquipmentDTO domainToDto(SpaceShipEquipmentDomain domain) {
        if ( domain == null ) {
            return null;
        }

        SpaceShipEquipmentDTO.SpaceShipEquipmentDTOBuilder spaceShipEquipmentDTO = SpaceShipEquipmentDTO.builder();

        spaceShipEquipmentDTO.id( domain.getId() );
        spaceShipEquipmentDTO.spaceShipId( domain.getSpaceShipId() );
        spaceShipEquipmentDTO.shipEquipment( domain.getShipEquipment() );

        return spaceShipEquipmentDTO.build();
    }

    @Override
    public Iterable<SpaceShipEquipmentDTO> domainToDto(Iterable<SpaceShipEquipmentDomain> domainList) {
        if ( domainList == null ) {
            return null;
        }

        ArrayList<SpaceShipEquipmentDTO> iterable = new ArrayList<SpaceShipEquipmentDTO>();
        for ( SpaceShipEquipmentDomain spaceShipEquipmentDomain : domainList ) {
            iterable.add( domainToDto( spaceShipEquipmentDomain ) );
        }

        return iterable;
    }

    @Override
    public List<SpaceShipEquipmentDomain> dtoToDomain(Iterable<SpaceShipEquipmentDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SpaceShipEquipmentDomain> list = new ArrayList<SpaceShipEquipmentDomain>();
        for ( SpaceShipEquipmentDTO spaceShipEquipmentDTO : dtoList ) {
            list.add( dtoToDomain( spaceShipEquipmentDTO ) );
        }

        return list;
    }

    @Override
    public void updateDomainWithDto(SpaceShipEquipmentDTO dtoSource, SpaceShipEquipmentDomain domainTarget) {
        if ( dtoSource == null ) {
            return;
        }

        domainTarget.setId( dtoSource.getId() );
        if ( dtoSource.getSpaceShipId() != null ) {
            domainTarget.setSpaceShipId( dtoSource.getSpaceShipId() );
        }
        if ( dtoSource.getShipEquipment() != null ) {
            domainTarget.setShipEquipment( dtoSource.getShipEquipment() );
        }
    }

    @Override
    public void updateDtoWithDomain(SpaceShipEquipmentDomain domainSource, SpaceShipEquipmentDTO dtoTarget) {
        if ( domainSource == null ) {
            return;
        }

        dtoTarget.setId( domainSource.getId() );
        if ( domainSource.getSpaceShipId() != null ) {
            dtoTarget.setSpaceShipId( domainSource.getSpaceShipId() );
        }
        if ( domainSource.getShipEquipment() != null ) {
            dtoTarget.setShipEquipment( domainSource.getShipEquipment() );
        }
    }
}
