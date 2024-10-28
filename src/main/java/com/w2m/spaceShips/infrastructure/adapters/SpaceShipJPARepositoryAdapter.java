package com.w2m.spaceShips.infrastructure.adapters;

import com.w2m.spaceShips.domain.enums.Equipment;
import com.w2m.spaceShips.domain.models.SpaceShipDomain;
import com.w2m.spaceShips.domain.ports.out.SpaceShipRepositoryPort;
import com.w2m.spaceShips.infrastructure.db.entities.SpaceShipEntity;
import com.w2m.spaceShips.infrastructure.db.mappers.SpaceShipDataBaseMapper;
import com.w2m.spaceShips.infrastructure.db.repositories.SpaceShipJPARepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author javiloguai
 */
@Component
public class SpaceShipJPARepositoryAdapter implements SpaceShipRepositoryPort {

    private final SpaceShipJPARepository spaceShipJPARepository;

    public SpaceShipJPARepositoryAdapter(final SpaceShipJPARepository spaceShipJPARepository) {
        this.spaceShipJPARepository = spaceShipJPARepository;
    }

    @Override
    public List<SpaceShipDomain> findAll() {
        return SpaceShipDataBaseMapper.INSTANCE.entityToDomain(spaceShipJPARepository.findAll());
    }

    @Override
    public Page<SpaceShipDomain> findAll(final Pageable pageable) {
        return SpaceShipDataBaseMapper.INSTANCE.entityToDomain(spaceShipJPARepository.findAll(pageable));
    }

    @Override
    public Page<SpaceShipDomain> findAllByName(final String name, final Pageable pageable) {
        return SpaceShipDataBaseMapper.INSTANCE.entityToDomain(
                spaceShipJPARepository.findByNameContainingIgnoreCase(name, pageable));
    }

    @Override
    public Optional<SpaceShipDomain> findFirstByName(String name) {
        return SpaceShipDataBaseMapper.INSTANCE.entityToDomain(spaceShipJPARepository.findFirstByNameIgnoreCase(name));
    }

    @Override
    public Optional<SpaceShipDomain> findById(final Long id) {
        return SpaceShipDataBaseMapper.INSTANCE.entityToDomain(spaceShipJPARepository.findById(id));
    }

    @Override
    public List<SpaceShipDomain> findByIdList(final List<Long> shipsIds) {
        return SpaceShipDataBaseMapper.INSTANCE.entityToDomain(spaceShipJPARepository.findAllById(shipsIds));
    }

    @Override
    public SpaceShipDomain save(final SpaceShipDomain spaceShipDomain) {
        SpaceShipEntity entity = SpaceShipDataBaseMapper.INSTANCE.domainToEntity(spaceShipDomain);
        return SpaceShipDataBaseMapper.INSTANCE.entityToDomain(spaceShipJPARepository.saveAndFlush(entity));
    }

    @Override
    public Optional<SpaceShipDomain> addEquipment(final Long id, final Equipment shipEquipment) {
        return Optional.empty();
    }

    @Override
    public void deleteById(final Long id) {
        spaceShipJPARepository.deleteById(id);
        spaceShipJPARepository.flush();
    }

    @Override
    public void deleteAll() {
        spaceShipJPARepository.deleteAll();
        spaceShipJPARepository.flush();
    }
}