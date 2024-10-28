package com.w2m.spaceShips.infrastructure.db.mappers;

import com.w2m.spaceShips.domain.models.SpaceShipDomain;
import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;
import com.w2m.spaceShips.infrastructure.db.entities.SpaceShipEntity;
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
public class SpaceShipDataBaseMapperImpl implements SpaceShipDataBaseMapper {

    @Override
    public SpaceShipEntity domainToEntity(SpaceShipDomain domain) {
        if ( domain == null ) {
            return null;
        }

        SpaceShipEntity.SpaceShipEntityBuilder spaceShipEntity = SpaceShipEntity.builder();

        spaceShipEntity.id( domain.getId() );
        spaceShipEntity.name( domain.getName() );
        spaceShipEntity.mediaShow( domain.getMediaShow() );
        spaceShipEntity.equipment( spaceShipEquipmentDomainListToSpaceShipEquipmentEntityList( domain.getEquipment() ) );

        return spaceShipEntity.build();
    }

    @Override
    public SpaceShipDomain entityToDomain(SpaceShipEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SpaceShipDomain.SpaceShipDomainBuilder spaceShipDomain = SpaceShipDomain.builder();

        spaceShipDomain.id( entity.getId() );
        spaceShipDomain.name( entity.getName() );
        spaceShipDomain.mediaShow( entity.getMediaShow() );
        spaceShipDomain.equipment( mapList( entity.getEquipment() ) );

        return spaceShipDomain.build();
    }

    @Override
    public Iterable<SpaceShipEntity> domainToEntity(Iterable<SpaceShipDomain> domainList) {
        if ( domainList == null ) {
            return null;
        }

        ArrayList<SpaceShipEntity> iterable = new ArrayList<SpaceShipEntity>();
        for ( SpaceShipDomain spaceShipDomain : domainList ) {
            iterable.add( domainToEntity( spaceShipDomain ) );
        }

        return iterable;
    }

    @Override
    public List<SpaceShipDomain> entityToDomain(Iterable<SpaceShipEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SpaceShipDomain> list = new ArrayList<SpaceShipDomain>();
        for ( SpaceShipEntity spaceShipEntity : entityList ) {
            list.add( entityToDomain( spaceShipEntity ) );
        }

        return list;
    }

    @Override
    public void updateDomainWithEntity(SpaceShipEntity entitySource, SpaceShipDomain domainTarget) {
        if ( entitySource == null ) {
            return;
        }

        domainTarget.setId( entitySource.getId() );
        if ( entitySource.getName() != null ) {
            domainTarget.setName( entitySource.getName() );
        }
        if ( entitySource.getMediaShow() != null ) {
            domainTarget.setMediaShow( entitySource.getMediaShow() );
        }
        if ( domainTarget.getEquipment() != null ) {
            List<SpaceShipEquipmentDomain> list = mapList( entitySource.getEquipment() );
            if ( list != null ) {
                domainTarget.getEquipment().clear();
                domainTarget.getEquipment().addAll( list );
            }
        }
        else {
            List<SpaceShipEquipmentDomain> list = mapList( entitySource.getEquipment() );
            if ( list != null ) {
                domainTarget.setEquipment( list );
            }
        }
    }

    @Override
    public void updateEntityWithDomain(SpaceShipDomain domainSource, SpaceShipEntity entityTarget) {
        if ( domainSource == null ) {
            return;
        }

        entityTarget.setId( domainSource.getId() );
        if ( domainSource.getName() != null ) {
            entityTarget.setName( domainSource.getName() );
        }
        if ( domainSource.getMediaShow() != null ) {
            entityTarget.setMediaShow( domainSource.getMediaShow() );
        }
        if ( entityTarget.getEquipment() != null ) {
            List<SpaceShipEquipmentEntity> list = spaceShipEquipmentDomainListToSpaceShipEquipmentEntityList( domainSource.getEquipment() );
            if ( list != null ) {
                entityTarget.getEquipment().clear();
                entityTarget.getEquipment().addAll( list );
            }
        }
        else {
            List<SpaceShipEquipmentEntity> list = spaceShipEquipmentDomainListToSpaceShipEquipmentEntityList( domainSource.getEquipment() );
            if ( list != null ) {
                entityTarget.setEquipment( list );
            }
        }
    }

    @Override
    public void copyToEntity(SpaceShipDomain domain, SpaceShipEntity entity) {
        if ( domain == null ) {
            return;
        }

        entity.setName( domain.getName() );
        entity.setMediaShow( domain.getMediaShow() );
        if ( entity.getEquipment() != null ) {
            List<SpaceShipEquipmentEntity> list = spaceShipEquipmentDomainListToSpaceShipEquipmentEntityList( domain.getEquipment() );
            if ( list != null ) {
                entity.getEquipment().clear();
                entity.getEquipment().addAll( list );
            }
            else {
                entity.setEquipment( null );
            }
        }
        else {
            List<SpaceShipEquipmentEntity> list = spaceShipEquipmentDomainListToSpaceShipEquipmentEntityList( domain.getEquipment() );
            if ( list != null ) {
                entity.setEquipment( list );
            }
        }
    }

    protected SpaceShipEquipmentEntity spaceShipEquipmentDomainToSpaceShipEquipmentEntity(SpaceShipEquipmentDomain spaceShipEquipmentDomain) {
        if ( spaceShipEquipmentDomain == null ) {
            return null;
        }

        SpaceShipEquipmentEntity.SpaceShipEquipmentEntityBuilder spaceShipEquipmentEntity = SpaceShipEquipmentEntity.builder();

        spaceShipEquipmentEntity.id( spaceShipEquipmentDomain.getId() );
        spaceShipEquipmentEntity.spaceShipId( spaceShipEquipmentDomain.getSpaceShipId() );
        spaceShipEquipmentEntity.shipEquipment( spaceShipEquipmentDomain.getShipEquipment() );

        return spaceShipEquipmentEntity.build();
    }

    protected List<SpaceShipEquipmentEntity> spaceShipEquipmentDomainListToSpaceShipEquipmentEntityList(List<SpaceShipEquipmentDomain> list) {
        if ( list == null ) {
            return null;
        }

        List<SpaceShipEquipmentEntity> list1 = new ArrayList<SpaceShipEquipmentEntity>( list.size() );
        for ( SpaceShipEquipmentDomain spaceShipEquipmentDomain : list ) {
            list1.add( spaceShipEquipmentDomainToSpaceShipEquipmentEntity( spaceShipEquipmentDomain ) );
        }

        return list1;
    }
}
