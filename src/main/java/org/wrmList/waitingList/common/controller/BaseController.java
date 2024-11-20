package org.wrmList.waitingList.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.shared.dto.PageDTO;
import org.wrmList.waitingList.util.annotation.IdExists;

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
    public PageDTO<ResponseDTO> findAll(Pageable pageable) {
        logger.info("Retrieving all entities with pagination");
        return getService().findAll(pageable);
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
