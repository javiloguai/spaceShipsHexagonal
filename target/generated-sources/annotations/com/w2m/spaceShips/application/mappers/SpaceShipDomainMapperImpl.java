package com.w2m.spaceShips.application.mappers;

import com.w2m.spaceShips.application.models.SpaceShipDTO;
import com.w2m.spaceShips.application.models.SpaceShipEquipmentDTO;
import com.w2m.spaceShips.domain.models.SpaceShipDomain;
import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-28T01:49:37+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 21 (Eclipse Adoptium)"
)
@Component
public class SpaceShipDomainMapperImpl implements SpaceShipDomainMapper {

    @Override
    public SpaceShipDomain dtoToDomain(SpaceShipDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SpaceShipDomain.SpaceShipDomainBuilder spaceShipDomain = SpaceShipDomain.builder();

        spaceShipDomain.id( dto.getId() );
        spaceShipDomain.name( dto.getName() );
        spaceShipDomain.mediaShow( dto.getMediaShow() );
        spaceShipDomain.equipment( mapDtoList( dto.getEquipment() ) );

        return spaceShipDomain.build();
    }

    @Override
    public SpaceShipDTO domainToDto(SpaceShipDomain domain) {
        if ( domain == null ) {
            return null;
        }

        SpaceShipDTO.SpaceShipDTOBuilder spaceShipDTO = SpaceShipDTO.builder();

        spaceShipDTO.id( domain.getId() );
        spaceShipDTO.name( domain.getName() );
        spaceShipDTO.mediaShow( domain.getMediaShow() );
        spaceShipDTO.equipment( mapList( domain.getEquipment() ) );

        return spaceShipDTO.build();
    }

    @Override
    public Iterable<SpaceShipDTO> domainToDto(Iterable<SpaceShipDomain> domainList) {
        if ( domainList == null ) {
            return null;
        }

        ArrayList<SpaceShipDTO> iterable = new ArrayList<SpaceShipDTO>();
        for ( SpaceShipDomain spaceShipDomain : domainList ) {
            iterable.add( domainToDto( spaceShipDomain ) );
        }

        return iterable;
    }

    @Override
    public List<SpaceShipDomain> dtoToDomain(Iterable<SpaceShipDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SpaceShipDomain> list = new ArrayList<SpaceShipDomain>();
        for ( SpaceShipDTO spaceShipDTO : dtoList ) {
            list.add( dtoToDomain( spaceShipDTO ) );
        }

        return list;
    }

    @Override
    public void updateDomainWithDto(SpaceShipDTO dtoSource, SpaceShipDomain domainTarget) {
        if ( dtoSource == null ) {
            return;
        }

        domainTarget.setId( dtoSource.getId() );
        if ( dtoSource.getName() != null ) {
            domainTarget.setName( dtoSource.getName() );
        }
        if ( dtoSource.getMediaShow() != null ) {
            domainTarget.setMediaShow( dtoSource.getMediaShow() );
        }
        if ( domainTarget.getEquipment() != null ) {
            List<SpaceShipEquipmentDomain> list = mapDtoList( dtoSource.getEquipment() );
            if ( list != null ) {
                domainTarget.getEquipment().clear();
                domainTarget.getEquipment().addAll( list );
            }
        }
        else {
            List<SpaceShipEquipmentDomain> list = mapDtoList( dtoSource.getEquipment() );
            if ( list != null ) {
                domainTarget.setEquipment( list );
            }
        }
    }

    @Override
    public void updateDtoWithDomain(SpaceShipDomain domainSource, SpaceShipDTO dtoTarget) {
        if ( domainSource == null ) {
            return;
        }

        dtoTarget.setId( domainSource.getId() );
        if ( domainSource.getName() != null ) {
            dtoTarget.setName( domainSource.getName() );
        }
        if ( domainSource.getMediaShow() != null ) {
            dtoTarget.setMediaShow( domainSource.getMediaShow() );
        }
        if ( dtoTarget.getEquipment() != null ) {
            List<SpaceShipEquipmentDTO> list = mapList( domainSource.getEquipment() );
            if ( list != null ) {
                dtoTarget.getEquipment().clear();
                dtoTarget.getEquipment().addAll( list );
            }
        }
        else {
            List<SpaceShipEquipmentDTO> list = mapList( domainSource.getEquipment() );
            if ( list != null ) {
                dtoTarget.setEquipment( list );
            }
        }
    }
}
