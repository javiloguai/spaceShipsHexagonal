package com.w2m.spaceShips.infrastructure.restapi.mappers.request;

import com.w2m.spaceShips.application.models.SpaceShipDTO;
import com.w2m.spaceShips.infrastructure.restapi.model.requests.SpaceShipRequest;
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
public class SpaceShipRequestMapperImpl implements SpaceShipRequestMapper {

    @Override
    public SpaceShipDTO fromRequestToDto(SpaceShipRequest input) {
        if ( input == null ) {
            return null;
        }

        SpaceShipDTO.SpaceShipDTOBuilder spaceShipDTO = SpaceShipDTO.builder();

        spaceShipDTO.name( input.getName() );
        spaceShipDTO.mediaShow( input.getMediaShow() );
        spaceShipDTO.equipment( mapList( input.getEquipment() ) );

        return spaceShipDTO.build();
    }

    @Override
    public List<SpaceShipDTO> fromRequestsToDtos(List<SpaceShipRequest> requests) {
        if ( requests == null ) {
            return null;
        }

        List<SpaceShipDTO> list = new ArrayList<SpaceShipDTO>( requests.size() );
        for ( SpaceShipRequest spaceShipRequest : requests ) {
            list.add( fromRequestToDto( spaceShipRequest ) );
        }

        return list;
    }
}
