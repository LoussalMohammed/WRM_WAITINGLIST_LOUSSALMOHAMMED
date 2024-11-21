package org.wrmList.waitingList.visitorWaitingList.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wrmList.waitingList.common.controller.BaseController;
import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.util.annotation.IdExists;
import org.wrmList.waitingList.visitorWaitingList.dto.CreateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.dto.ResponseVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.dto.UpdateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;
import org.wrmList.waitingList.visitorWaitingList.entity.embeddable.VisitKey;
import org.wrmList.waitingList.visitorWaitingList.service.VisitorWaitingListService;

import org.wrmList.waitingList.waitingList.entity.WaitingList;

import java.time.LocalTime;
import java.util.List;

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
    ResponseVisitorWaitingListDTO updateById(@RequestBody UpdateVisitorWaitingListDTO updateVisitorWaitingListDTO) {
        return visitorWaitingListService.updateById(updateVisitorWaitingListDTO);
    }

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    ResponseVisitorWaitingListDTO findById(@RequestBody VisitKey id) {
        return visitorWaitingListService.findById(id);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    void deleteById(@RequestBody VisitKey id) {
        visitorWaitingListService.deleteById(id);
    }

    @PatchMapping("/setInProgress")
    public ResponseEntity<ResponseVisitorWaitingListDTO> setInProgress(@IdExists(entityClass = VisitorWaitingListController.class, field = "visitKey") @RequestBody VisitKey id) {
        return ResponseEntity.ok(visitorWaitingListService.setInProgressById(id));
    }

    @PatchMapping("/setDone")
    public ResponseEntity<ResponseVisitorWaitingListDTO> setDone(@IdExists(entityClass = VisitorWaitingListController.class, field = "visitKey") @RequestBody VisitKey id) {
        return ResponseEntity.ok(visitorWaitingListService.setDone(id));
    }

    @PatchMapping("/setCanceled")
    public ResponseEntity<ResponseVisitorWaitingListDTO> setCanceled(@IdExists(entityClass = VisitorWaitingListController.class, field = "visitKey") @RequestBody VisitKey id) {
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
