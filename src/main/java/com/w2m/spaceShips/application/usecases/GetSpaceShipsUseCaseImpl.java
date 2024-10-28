package com.w2m.spaceShips.application.usecases;

import com.w2m.spaceShips.domain.models.SpaceShipDomain;
import com.w2m.spaceShips.domain.ports.in.GetSpaceShipsUseCase;
import com.w2m.spaceShips.domain.ports.out.SpaceShipRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author javiloguai
 */
@Component
public class GetSpaceShipsUseCaseImpl implements GetSpaceShipsUseCase {

    private final SpaceShipRepositoryPort spaceShipRepositoryPort;

    public GetSpaceShipsUseCaseImpl(final SpaceShipRepositoryPort spaceShipRepositoryPort) {
        this.spaceShipRepositoryPort = spaceShipRepositoryPort;
    }

    @Override
    public List<SpaceShipDomain> getAllSpaceShips() {
        return spaceShipRepositoryPort.findAll();
    }

    @Override
    public Page<SpaceShipDomain> pageAllSpaceShips(final Pageable pageable) {
        return spaceShipRepositoryPort.findAll(pageable);
    }

    @Override
    public Optional<SpaceShipDomain> findFirstByName(String name) {
        return spaceShipRepositoryPort.findFirstByName(name);
    }

    @Override
    public Page<SpaceShipDomain> pageAllSpaceShipsByName(final String name, final Pageable pageable) {
        return spaceShipRepositoryPort.findAllByName(name, pageable);

    }

    @Override
    public Optional<SpaceShipDomain> findSpaceShipById(final Long id) {
        return spaceShipRepositoryPort.findById(id);
    }

    @Override
    public List<SpaceShipDomain> findSpaceShipsByIdList(List<Long> spaceShipIds) {
        return spaceShipRepositoryPort.findByIdList(spaceShipIds);
    }

}
