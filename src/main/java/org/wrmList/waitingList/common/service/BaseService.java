package org.wrmList.waitingList.common.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.wrmList.waitingList.shared.dto.PageDTO;

public interface BaseService<Long, C, U, R> {
    R create(@Valid C requestDTO);
    PageDTO<R> findAll(Pageable pageable);
    R findById(Long id);
    R updateById(U updateDTO, Long id);
    void deleteById(Long id);
}
