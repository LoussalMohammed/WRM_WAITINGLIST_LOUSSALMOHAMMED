package org.wrmList.waitingList.common.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.wrmList.waitingList.shared.dto.response.PageResponse;

import java.util.Map;

public interface BaseService<Long, C, U, R> {
    R create(@Valid C requestDTO);
    PageResponse<R> findAll(Pageable pageable, Map<String, String> filters);
    R findById(Long id);
    R updateById(U updateDTO, Long id);
    void deleteById(Long id);
}
