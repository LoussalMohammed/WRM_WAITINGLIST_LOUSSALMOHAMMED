package org.wrmList.waitingList.visitorWaitingList.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wrmList.waitingList.common.controller.BaseController;
import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.util.annotation.IdExists;
import org.wrmList.waitingList.visitorWaitingList.dto.request.CreateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.dto.response.ResponseVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.dto.request.UpdateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitKey;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;
import org.wrmList.waitingList.visitorWaitingList.service.VisitorWaitingListService;

import org.wrmList.waitingList.waitingList.entity.WaitingList;

import java.time.*;
import java.util.*;

// Visitor Waiting List Controller

@RestController
@RequestMapping("/visitorWaitingList")
@RequiredArgsConstructor
@Validated
public class VisitorWaitingListController extends BaseController<VisitorWaitingListController, Long,CreateVisitorWaitingListDTO, UpdateVisitorWaitingListDTO, ResponseVisitorWaitingListDTO> {
    private final VisitorWaitingListService visitorWaitingListService;

    @Override
    protected BaseService<Long, CreateVisitorWaitingListDTO, UpdateVisitorWaitingListDTO, ResponseVisitorWaitingListDTO> getService() {
        return visitorWaitingListService;
    }

    @Override
    public Class<VisitorWaitingListController> getEntityClass() {
        return VisitorWaitingListController.class;
    }

    @PatchMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    ResponseVisitorWaitingListDTO updateById(@RequestBody UpdateVisitorWaitingListDTO updateVisitorWaitingListDTO)   {
        return visitorWaitingListService.updateById(updateVisitorWaitingListDTO);
    }

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    ResponseVisitorWaitingListDTO findById(@IdExists(entityClass = VisitorWaitingList.class, field = "id") @RequestBody VisitKey id) {
        return visitorWaitingListService.findById(id);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    void deleteById(@IdExists(entityClass = VisitorWaitingList.class, field = "id") @RequestBody VisitKey id) {
        visitorWaitingListService.deleteById(id);
    }

    @PatchMapping("/setInProgress")
    public ResponseEntity<ResponseVisitorWaitingListDTO> setInProgress(@IdExists(entityClass = VisitorWaitingList.class, field = "id") @RequestBody VisitKey id) {
        return ResponseEntity.ok(visitorWaitingListService.setInProgressById(id));
    }

    @PatchMapping("/setDone")
    public ResponseEntity<ResponseVisitorWaitingListDTO> setDone(@IdExists(entityClass = VisitorWaitingList.class, field = "id") @RequestBody VisitKey id) {
        return ResponseEntity.ok(visitorWaitingListService.setDone(id));
    }

    @PatchMapping("/setCanceled")
    public ResponseEntity<ResponseVisitorWaitingListDTO> setCanceled(@IdExists(entityClass = VisitorWaitingList.class, field = "id") @RequestBody VisitKey id) {
        return ResponseEntity.ok(visitorWaitingListService.setCanceled(id));
    }

    @GetMapping("/average/{id}")
    public LocalTime getAverageWaitingTime(
            @Validated @IdExists(entityClass = WaitingList.class, field = "id") @PathVariable Long id ) {
        return visitorWaitingListService.getAverageTime(id);
    }

    @GetMapping("/orderedWaitingList/{id}")
    public List<ResponseVisitorWaitingListDTO> getWaitingListOrdered(
            @Validated @IdExists(entityClass = WaitingList.class, field = "id") @PathVariable Long id) {
        return visitorWaitingListService.findByWaitingListId(id);
    }

}
