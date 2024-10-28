package com.w2m.spaceShips.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author javiloguai
 * <p>
 * Immutable class for storing spaceship's information from an external resource
 */

@AllArgsConstructor
@Getter
public class SpaceShipExternalInfoDomain {
    private final long id;

    private final Long spaceShipId;

    private final String externalInfo;

}
