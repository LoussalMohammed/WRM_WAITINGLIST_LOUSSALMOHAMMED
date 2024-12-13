package org.wrmList.waitingList.common.serviceImpl;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.shared.dto.response.PageResponse;
import org.wrmList.waitingList.shared.exception.ResourceNotFoundException;

import java.util.Map;
//Base Service Impl
public abstract class BaseServiceImpl<E, Long, C, U, R, V> implements BaseService<Long, C, U, R> {
    protected final JpaRepository<E, Long> repository;
    protected final BaseMapper<C, E> createMapper;
    protected final BaseMapper<U, E> updateMapper;
    protected final BaseMapper<R, E> responseMapper;

    protected BaseServiceImpl(JpaRepository<E, Long> repository,
                              BaseMapper<C, E> createMapper,
                              BaseMapper<U, E> updateMapper,
                              BaseMapper<R, E> responseMapper) {
        this.repository = repository;
        this.createMapper = createMapper;
        this.updateMapper = updateMapper;
        this.responseMapper = responseMapper;
    }


    @Override
    public R create(@Valid C requestDTO) {
        E entity = createMapper.toOT(requestDTO);
        return responseMapper.toE(repository.save(entity));
    }

    @Override
    public PageResponse<R> findAll(Pageable pageable, Map<String, String> filters) {
        var page = repository.findAll(pageable);
        var items = page.getContent().stream().map(this::toResponse).toList();
        return PageResponse.of(items, page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public R findById(Long id) {
        E entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return responseMapper.toE(entity);
    }

    @Override
    public R updateById(U updateDTO, Long id) {
        E entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        updateMapper.toE(entity);
        return responseMapper.toE(repository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    protected abstract R toResponse(E entity);
}