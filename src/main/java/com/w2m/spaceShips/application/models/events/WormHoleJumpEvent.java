package com.w2m.spaceShips.application.models.events;

import java.io.Serializable;

/**
 * @author javiloguai
 * <p>
 * KAfka event definition
 */
public record WormHoleJumpEvent(Long id, String name) implements Serializable {
}
