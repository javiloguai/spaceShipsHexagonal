package com.w2m.spaceShips.domain.ports.out;

import com.w2m.spaceShips.domain.models.SpaceShipExternalInfoDomain;

/**
 * @author javiloguai
 */
public interface ExternalServicePort {

    SpaceShipExternalInfoDomain getSpaceShipExternalInfo(final long spaceShipId);
}
