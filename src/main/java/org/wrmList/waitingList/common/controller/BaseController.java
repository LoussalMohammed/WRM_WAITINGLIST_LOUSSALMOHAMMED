package org.wrmList.waitingList.common.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.shared.dto.request.PaginationRequest;
import org.wrmList.waitingList.shared.dto.response.PageResponse;
import org.wrmList.waitingList.util.annotation.IdExists;

import java.util.Map;

@RequestMapping
@RestController
@Validated
public abstract class BaseController<E, ID, CreateDTO, UpdateDTO, ResponseDTO> {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected abstract BaseService<ID, CreateDTO, UpdateDTO, ResponseDTO> getService();
    public abstract Class<E> getEntityClass();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO create(@RequestBody CreateDTO createDTO) {
        logger.info("Creating entity with data: {}", createDTO);
        return getService().create(createDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO findById(@Validated @IdExists(entityClass = Object.class, field = "id") @PathVariable ID id) {
        logger.info("Finding entity by ID: {}", id);
        return getService().findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ResponseDTO> findAll(
            @Valid PaginationRequest pagination,
            @RequestParam(required = false) Map<String, String> filters
    ) {
        PaginationRequest paginationRequest = pagination != null ? pagination :
                PaginationRequest.of(
                        PaginationRequest.DEFAULT_PAGE,
                        PaginationRequest.DEFAULT_SIZE,
                        "id",
                        "asc"
                );

        Sort sort = Sort.by(
                paginationRequest.sortDirection() == null ||
                        paginationRequest.sortDirection().equalsIgnoreCase("asc")
                        ? Sort.Direction.ASC
                        : Sort.Direction.DESC,
                paginationRequest.sortBy() == null ? "id" : paginationRequest.sortBy()
        );

        Pageable pageable = PageRequest.of(
                paginationRequest.page(),
                paginationRequest.size(),
                sort
        );

        return getService().findAll(pageable, filters);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO updateById(@RequestBody UpdateDTO updateDTO,@Validated @IdExists(entityClass = Object.class, field = "id") @PathVariable ID id) {
        logger.info("Updating entity with ID: {}", id);
        return getService().updateById(updateDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Validated @IdExists(entityClass = Object.class, field = "id") @PathVariable ID id) {
        logger.info("Deleting entity with ID: {}", id);
        getService().deleteById(id);
    }
}
