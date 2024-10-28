package com.w2m.spaceShips.infrastructure.db.mappers;

import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;
import com.w2m.spaceShips.infrastructure.db.entities.SpaceShipEquipmentEntity;
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
public class SpaceShipEquipmentDataBaseMapperImpl implements SpaceShipEquipmentDataBaseMapper {

    @Override
    public SpaceShipEquipmentEntity domainToEntity(SpaceShipEquipmentDomain domain) {
        if ( domain == null ) {
            return null;
        }

        SpaceShipEquipmentEntity.SpaceShipEquipmentEntityBuilder spaceShipEquipmentEntity = SpaceShipEquipmentEntity.builder();

        spaceShipEquipmentEntity.id( domain.getId() );
        spaceShipEquipmentEntity.spaceShipId( domain.getSpaceShipId() );
        spaceShipEquipmentEntity.shipEquipment( domain.getShipEquipment() );

        return spaceShipEquipmentEntity.build();
    }

    @Override
    public SpaceShipEquipmentDomain entityToDomain(SpaceShipEquipmentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SpaceShipEquipmentDomain.SpaceShipEquipmentDomainBuilder spaceShipEquipmentDomain = SpaceShipEquipmentDomain.builder();

        spaceShipEquipmentDomain.id( entity.getId() );
        spaceShipEquipmentDomain.spaceShipId( entity.getSpaceShipId() );
        spaceShipEquipmentDomain.shipEquipment( entity.getShipEquipment() );

        return spaceShipEquipmentDomain.build();
    }

    @Override
    public Iterable<SpaceShipEquipmentEntity> domainToEntity(Iterable<SpaceShipEquipmentDomain> domainList) {
        if ( domainList == null ) {
            return null;
        }

        ArrayList<SpaceShipEquipmentEntity> iterable = new ArrayList<SpaceShipEquipmentEntity>();
        for ( SpaceShipEquipmentDomain spaceShipEquipmentDomain : domainList ) {
            iterable.add( domainToEntity( spaceShipEquipmentDomain ) );
        }

        return iterable;
    }

    @Override
    public List<SpaceShipEquipmentDomain> entityToDomain(Iterable<SpaceShipEquipmentEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SpaceShipEquipmentDomain> list = new ArrayList<SpaceShipEquipmentDomain>();
        for ( SpaceShipEquipmentEntity spaceShipEquipmentEntity : entityList ) {
            list.add( entityToDomain( spaceShipEquipmentEntity ) );
        }

        return list;
    }

    @Override
    public void updateDomainWithEntity(SpaceShipEquipmentEntity entitySource, SpaceShipEquipmentDomain domainTarget) {
        if ( entitySource == null ) {
            return;
        }

        domainTarget.setId( entitySource.getId() );
        if ( entitySource.getSpaceShipId() != null ) {
            domainTarget.setSpaceShipId( entitySource.getSpaceShipId() );
        }
        if ( entitySource.getShipEquipment() != null ) {
            domainTarget.setShipEquipment( entitySource.getShipEquipment() );
        }
    }

    @Override
    public void updateEntityWithDomain(SpaceShipEquipmentDomain domainSource, SpaceShipEquipmentEntity entityTarget) {
        if ( domainSource == null ) {
            return;
        }

        entityTarget.setId( domainSource.getId() );
        if ( domainSource.getSpaceShipId() != null ) {
            entityTarget.setSpaceShipId( domainSource.getSpaceShipId() );
        }
        if ( domainSource.getShipEquipment() != null ) {
            entityTarget.setShipEquipment( domainSource.getShipEquipment() );
        }
    }
}
