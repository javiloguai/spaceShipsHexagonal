package com.w2m.spaceShips.infrastructure.restapi.mappers.request;

/**
 * Mapper from requests to domain dto
 *
 * @param <I> the request dto
 * @param <R> the domain dto
 */
public interface RequestMapper<I, R> {

    /**
     * From request to dto r.
     *
     * @param input the input
     * @return the r
     */
    R fromRequestToDto(I input);

}
