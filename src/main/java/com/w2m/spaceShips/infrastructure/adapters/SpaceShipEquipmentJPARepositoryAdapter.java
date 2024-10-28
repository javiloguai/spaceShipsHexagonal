package com.w2m.spaceShips.infrastructure.adapters;

import com.w2m.spaceShips.domain.enums.Equipment;
import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;
import com.w2m.spaceShips.domain.ports.out.SpaceShipEquipmentRepositoryPort;
import com.w2m.spaceShips.infrastructure.db.entities.SpaceShipEquipmentEntity;
import com.w2m.spaceShips.infrastructure.db.mappers.SpaceShipEquipmentDataBaseMapper;
import com.w2m.spaceShips.infrastructure.db.repositories.SpaceShipEquipmentJPARepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author javiloguai
 */
@Component
public class SpaceShipEquipmentJPARepositoryAdapter implements SpaceShipEquipmentRepositoryPort {

    private final SpaceShipEquipmentJPARepository spaceShipEquipmentJPARepository;

    public SpaceShipEquipmentJPARepositoryAdapter(final SpaceShipEquipmentJPARepository spaceShipEquipmentJPARepository) {
        this.spaceShipEquipmentJPARepository = spaceShipEquipmentJPARepository;
    }

    @Override
    public List<SpaceShipEquipmentDomain> findAll() {
        return SpaceShipEquipmentDataBaseMapper.INSTANCE.entityToDomain(spaceShipEquipmentJPARepository.findAll());
    }

    @Override
    public List<SpaceShipEquipmentDomain> findAllByShipEquipment(final Equipment shipEquipment) {

        return SpaceShipEquipmentDataBaseMapper.INSTANCE.entityToDomain(spaceShipEquipmentJPARepository.findByShipEquipment(shipEquipment));
    }

    @Override
    public SpaceShipEquipmentDomain save(final SpaceShipEquipmentDomain equipmentDomain) {
        SpaceShipEquipmentEntity entity = SpaceShipEquipmentDataBaseMapper.INSTANCE.domainToEntity(equipmentDomain);
        return SpaceShipEquipmentDataBaseMapper.INSTANCE.entityToDomain(spaceShipEquipmentJPARepository.saveAndFlush(entity));
    }

    @Override
    public void saveAll(final List<SpaceShipEquipmentDomain> equipment) {
        spaceShipEquipmentJPARepository.saveAllAndFlush(SpaceShipEquipmentDataBaseMapper.INSTANCE.domainToEntity(equipment));
    }

    @Override
    public void deleteAll() {
        spaceShipEquipmentJPARepository.deleteAll();
        spaceShipEquipmentJPARepository.flush();
    }

    @Override
    public void deleteAllBySpaceShipId(final Long spaceShipId) {
        spaceShipEquipmentJPARepository.deleteAllBySpaceShipId(spaceShipId);
        spaceShipEquipmentJPARepository.flush();
    }
}