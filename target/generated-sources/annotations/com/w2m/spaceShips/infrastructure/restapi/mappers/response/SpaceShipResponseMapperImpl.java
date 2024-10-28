package com.w2m.spaceShips.infrastructure.restapi.mappers.response;

import com.w2m.spaceShips.domain.enums.Equipment;
import com.w2m.spaceShips.domain.models.SpaceShipDomain;
import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;
import com.w2m.spaceShips.infrastructure.restapi.model.responses.SpaceShipResponse;
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
public class SpaceShipResponseMapperImpl implements SpaceShipResponseMapper {

    @Override
    public SpaceShipResponse toResponse(SpaceShipDomain input) {
        if ( input == null ) {
            return null;
        }

        SpaceShipResponse spaceShipResponse = new SpaceShipResponse();

        spaceShipResponse.setId( String.valueOf( input.getId() ) );
        spaceShipResponse.setName( input.getName() );
        spaceShipResponse.setMediaShow( input.getMediaShow() );
        spaceShipResponse.setEquipment( spaceShipEquipmentDomainListToEquipmentList( input.getEquipment() ) );

        return spaceShipResponse;
    }

    @Override
    public List<SpaceShipResponse> toResponses(List<SpaceShipDomain> inputs) {
        if ( inputs == null ) {
            return null;
        }

        List<SpaceShipResponse> list = new ArrayList<SpaceShipResponse>( inputs.size() );
        for ( SpaceShipDomain spaceShipDomain : inputs ) {
            list.add( toResponse( spaceShipDomain ) );
        }

        return list;
    }

    protected List<Equipment> spaceShipEquipmentDomainListToEquipmentList(List<SpaceShipEquipmentDomain> list) {
        if ( list == null ) {
            return null;
        }

        List<Equipment> list1 = new ArrayList<Equipment>( list.size() );
        for ( SpaceShipEquipmentDomain spaceShipEquipmentDomain : list ) {
            list1.add( map( spaceShipEquipmentDomain ) );
        }

        return list1;
    }
}
