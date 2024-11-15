package org.wrmList.waitingList.common.service;

import org.springframework.data.domain.Pageable;
import org.wrmList.waitingList.shared.dto.PageDTO;

public interface BaseService<Long, C, U, R> {
    R create(C requestDTO);
    PageDTO<R> findAll(Pageable pageable);
    R findById(Long id);
    R updateById(U updateDTO, Long id);
    void deleteById(Long id);
}
