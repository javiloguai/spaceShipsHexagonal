package com.w2m.spaceShips.application.services.impl.impl;

import com.w2m.spaceShips.application.exception.AlreadyExistException;
import com.w2m.spaceShips.application.exception.BusinessRuleViolatedException;
import com.w2m.spaceShips.application.exception.NotFoundException;
import com.w2m.spaceShips.application.mappers.SpaceShipDomainMapper;
import com.w2m.spaceShips.application.mappers.SpaceShipEquipmentDomainMapper;
import com.w2m.spaceShips.application.models.SpaceShipDTO;
import com.w2m.spaceShips.application.services.impl.SpaceShipApplicationService;
import com.w2m.spaceShips.domain.enums.Equipment;
import com.w2m.spaceShips.domain.models.SpaceShipDomain;
import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;
import com.w2m.spaceShips.domain.ports.in.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author javiloguai
 * I put some examples of other kind of call I will not implement anything on other examples
 */
@Service
@Validated
@Transactional
public class SpaceShipApplicationServiceImpl implements SpaceShipApplicationService {

    private static final String ID_MANDATORY = "Id field is Mandatory";

    private static final String NAME_EMPTY = "SpaceShip's name cannot be empty";

    private static final String EQUIPMENT_EMPTY = "SpaceShip's equipment list cannot be empty";

    private static final String PAGE_MANDATORY = "page info is Mandatory";

    private static final String NAME_MANDATORY = "name field is Mandatory";

    private static final String EQUIPMENT_MANDATORY = "equipment field is Mandatory";

    private static final String SPACE_SHIP_MANDATORY = "The SpaceShip Object is Mandatory";

    private final GetSpaceShipsUseCase getSpaceShipsUseCase;

    private final GetShipEquipmentUseCase getShipEquipmentUseCase;

    private final CreateSpaceShipUseCase createSpaceShipUseCase;

    private final CreateShipEquipmentUseCase createShipEquipmentUseCase;

    private final UpdateSpaceShipUseCase updateSpaceShipUseCase;

    private final DeleteSpaceShipUseCase deleteSpaceShipUseCase;

    private final DeleteShipEquipmentUseCase deleteShipEquipmentUseCase;

    public SpaceShipApplicationServiceImpl(final GetSpaceShipsUseCase getSpaceShipsUseCase,
                                           final GetShipEquipmentUseCase getShipEquipmentUseCase,
                                           final CreateSpaceShipUseCase createSpaceShipUseCase,
                                           final CreateShipEquipmentUseCase createShipEquipmentUseCase,
                                           final UpdateSpaceShipUseCase updateSpaceShipUseCase,
                                           final DeleteSpaceShipUseCase deleteSpaceShipUseCase,
                                           final DeleteShipEquipmentUseCase deleteShipEquipmentUseCase) {
        this.getSpaceShipsUseCase = getSpaceShipsUseCase;
        this.getShipEquipmentUseCase = getShipEquipmentUseCase;
        this.createSpaceShipUseCase = createSpaceShipUseCase;
        this.createShipEquipmentUseCase = createShipEquipmentUseCase;
        this.updateSpaceShipUseCase = updateSpaceShipUseCase;
        this.deleteSpaceShipUseCase = deleteSpaceShipUseCase;
        this.deleteShipEquipmentUseCase = deleteShipEquipmentUseCase;
    }

    @Override
    @Cacheable(cacheNames = "allships")
    public List<SpaceShipDomain> getAllSpaceShips() {
        return getSpaceShipsUseCase.getAllSpaceShips();
    }

    @Override
    @Cacheable(cacheNames = "pagedallships")
    public Page<SpaceShipDomain> pageAllSpaceShips(final Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new BusinessRuleViolatedException(PAGE_MANDATORY);
        }

        return getSpaceShipsUseCase.pageAllSpaceShips(pageable);
    }

    @Override
    @Cacheable(cacheNames = "pagedships", key = "#name")
    public Page<SpaceShipDomain> pageAllSpaceShipsByName(final String name, final Pageable pageable) {
        if (!StringUtils.hasText(name)) {
            throw new BusinessRuleViolatedException(NAME_MANDATORY);
        }
        if (Objects.isNull(pageable)) {
            throw new BusinessRuleViolatedException(PAGE_MANDATORY);
        }

        return getSpaceShipsUseCase.pageAllSpaceShipsByName(name, pageable);
    }

    @Override
    @Cacheable(cacheNames = "equipment", key = "#shipEquipment")
    public List<SpaceShipDomain> getAllSpaceShipsByEquipment(final Equipment shipEquipment) {
        if (shipEquipment == null) {
            throw new BusinessRuleViolatedException(EQUIPMENT_MANDATORY);

        }
        List<SpaceShipEquipmentDomain> equipmentDo = getShipEquipmentUseCase.findAllEquipmentEquipment(shipEquipment);
        List<Long> shipsIds = equipmentDo.stream().map(p -> p.getSpaceShipId()).distinct().toList();

        return getSpaceShipsUseCase.findSpaceShipsByIdList(shipsIds);
    }

    @Override
    @Cacheable(cacheNames = "ship", key = "#id")
    public SpaceShipDomain findSpaceShipById(final Long id) {
        if (id == null) {
            throw new BusinessRuleViolatedException(ID_MANDATORY);
        }
        return this.getSpaceShipById(id);
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "ship", allEntries = true),
            @CacheEvict(value = "pagedships", allEntries = true), @CacheEvict(value = "allships", allEntries = true),
            @CacheEvict(value = "pagedallships", allEntries = true),
            @CacheEvict(value = "equipment", allEntries = true)})
    public SpaceShipDomain createSpaceShip(final SpaceShipDTO spaceShipDTO) {

        this.validateSpaceShipData(spaceShipDTO);
        this.checkIfSpaceShipAlreadyExists(spaceShipDTO.getName());
        SpaceShipDomain shipDO = SpaceShipDomainMapper.INSTANCE.dtoToDomain(spaceShipDTO);

        shipDO = createSpaceShipUseCase.createSpaceShip(shipDO);

        final Long shipId = shipDO.getId();

        List<SpaceShipEquipmentDomain> equipmentDo = shipDO.getEquipment();

        equipmentDo.forEach(h -> h.setSpaceShipId(shipId));

        createShipEquipmentUseCase.assignAllEquipment(equipmentDo);

        return this.getSpaceShipById(shipId);
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "ship", allEntries = true),
            @CacheEvict(value = "pagedships", allEntries = true), @CacheEvict(value = "allships", allEntries = true),
            @CacheEvict(value = "pagedallships", allEntries = true),
            @CacheEvict(value = "equipment", allEntries = true)})
    public SpaceShipDomain updateSpaceShip(final Long id, final SpaceShipDTO spaceShipDTO) {

        if (id == null) {
            throw new BusinessRuleViolatedException(ID_MANDATORY);
        }
        this.validateSpaceShipData(spaceShipDTO);
        this.checkIfSpaceShipAlreadyExists(id, spaceShipDTO.getName());
        SpaceShipDomain shipDo = this.getSpaceShipById(id);

        shipDo.setName(spaceShipDTO.getName());
        shipDo.setMediaShow(spaceShipDTO.getMediaShow());

        shipDo.setEquipment(new ArrayList<>());

        updateSpaceShipUseCase.updateSpaceShip(shipDo);

        List<SpaceShipEquipmentDomain> equipmentDo = new ArrayList<>();
        equipmentDo.addAll(SpaceShipEquipmentDomainMapper.INSTANCE.dtoToDomain(spaceShipDTO.getEquipment()));

        deleteShipEquipmentUseCase.deleteAllBySpaceShipId(id);

        equipmentDo.forEach(h -> h.setSpaceShipId(id));

        createShipEquipmentUseCase.assignAllEquipment(equipmentDo);

        return this.getSpaceShipById(id);
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "ship", allEntries = true),
            @CacheEvict(value = "pagedships", allEntries = true), @CacheEvict(value = "allships", allEntries = true),
            @CacheEvict(value = "pagedallships", allEntries = true),
            @CacheEvict(value = "equipment", allEntries = true)})
    public SpaceShipDomain addEquipment(final Long id, final Equipment shipEquipment) {

        validateAddEquipment(id, shipEquipment);

        SpaceShipEquipmentDomain shipEquipmentToAdd = SpaceShipEquipmentDomain.builder().spaceShipId(id).shipEquipment(shipEquipment).build();

        createShipEquipmentUseCase.addShipEquipment(shipEquipmentToAdd);

        return this.getSpaceShipById(id);
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "ship", allEntries = true),
            @CacheEvict(value = "pagedships", allEntries = true), @CacheEvict(value = "allships", allEntries = true),
            @CacheEvict(value = "pagedallships", allEntries = true),
            @CacheEvict(value = "equipment", allEntries = true)})
    public void deleteSpaceShipById(final Long id) {
        if (id == null) {
            throw new BusinessRuleViolatedException(ID_MANDATORY);
        }
        this.getSpaceShipById(id);

        deleteShipEquipmentUseCase.deleteAllBySpaceShipId(id);

        deleteSpaceShipUseCase.deleteSpaceShipById(id);

    }

    @Override
    @Caching(evict = {@CacheEvict(value = "ship", allEntries = true),
            @CacheEvict(value = "pagedships", allEntries = true), @CacheEvict(value = "allships", allEntries = true),
            @CacheEvict(value = "pagedallships", allEntries = true),
            @CacheEvict(value = "equipment", allEntries = true)})
    public void deleteAllSpaceShips() {
        if (!getShipEquipmentUseCase.findAllEquipment().isEmpty()) {
            deleteShipEquipmentUseCase.deleteAll();
        }
        if (!getSpaceShipsUseCase.getAllSpaceShips().isEmpty()) {
            deleteSpaceShipUseCase.deleteAllSpaceShips();
        }

    }

    private void validateAddEquipment(final Long id, final Equipment shipEquipment) {

        if (id == null) {
            throw new BusinessRuleViolatedException(ID_MANDATORY);
        }
        if (shipEquipment == null) {
            throw new BusinessRuleViolatedException(EQUIPMENT_MANDATORY);
        }

        SpaceShipDomain ship = this.getSpaceShipById(id);

        List<SpaceShipEquipmentDomain> equipmentDomains = ship.getEquipment();
        List<Equipment> equipment = equipmentDomains.stream().map(p -> p.getShipEquipment()).distinct().toList();

        if (!equipment.isEmpty() && equipment.contains(shipEquipment)) {
            throw new AlreadyExistException("This SpaceShip already has that ship equipment: " + shipEquipment);
        }
    }

    private SpaceShipDomain getSpaceShipById(final Long id) {
        return getSpaceShipsUseCase.findSpaceShipById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found SpaceShip with id %s", id)));
    }

    private void validateSpaceShipData(final SpaceShipDTO spaceShipDTO) {
        if (spaceShipDTO == null) {
            throw new BusinessRuleViolatedException(SPACE_SHIP_MANDATORY);
        }
        if (!StringUtils.hasText(spaceShipDTO.getName())) {
            throw new BusinessRuleViolatedException(NAME_EMPTY);
        }
        if (CollectionUtils.isEmpty(spaceShipDTO.getEquipment())) {
            throw new BusinessRuleViolatedException(EQUIPMENT_EMPTY);
        }
    }

    private void checkIfSpaceShipAlreadyExists(final String spaceShipName) {
        getSpaceShipsUseCase.findFirstByName(spaceShipName).ifPresent(this::throwAlreadyExistException);
    }

    private void checkIfSpaceShipAlreadyExists(final Long id, final String spaceShipName) {
        getSpaceShipsUseCase.findFirstByName(spaceShipName).ifPresent(h -> {
            if (h.getId() != id.longValue()) {
                throwAlreadyExistException(h);
            }
        });
    }

    private void throwAlreadyExistException(final SpaceShipDomain spaceShipDomain) {
        throw new AlreadyExistException("This SpaceShip already exists: " + spaceShipDomain.toString());
    }

}
