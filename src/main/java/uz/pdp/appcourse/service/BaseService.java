package uz.pdp.appcourse.service;

import uz.pdp.appcourse.dto.ApiResponse;

import java.util.List;

/**
 * @param <CD> createdDto
 * @param <RD> ResponseDto
 * @param <E> Entity
 * @param <K> Id Long
 */
public interface BaseService <CD,RD,K,E>{

    ApiResponse<CD> create(CD cd);

    ApiResponse<Void> delete(K id);

    ApiResponse<Void> update (K id,CD cd);

    ApiResponse<List<E>> getAll();

    ApiResponse<RD> getById (K id);
}
