package org.wrmList.waitingList.visitorWaitingList.controller;

import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wrmList.waitingList.common.controller.BaseController;
import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.shared.dto.PageDTO;
import org.wrmList.waitingList.util.annotation.IdExists;
import org.wrmList.waitingList.visitorWaitingList.dto.CreateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.dto.ResponseVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.dto.UpdateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.entity.embeddable.VisitKey;
import org.wrmList.waitingList.visitorWaitingList.service.VisitorWaitingListService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.wrmList.waitingList.waitingList.entity.WaitingList;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/visitorWaitingList")
@RequiredArgsConstructor
@Validated
public class VisitorWaitingList extends BaseController<VisitorWaitingList, Long,CreateVisitorWaitingListDTO, UpdateVisitorWaitingListDTO, ResponseVisitorWaitingListDTO> {
    private final VisitorWaitingListService visitorWaitingListService;

    @Override
    protected BaseService<Long, CreateVisitorWaitingListDTO, UpdateVisitorWaitingListDTO, ResponseVisitorWaitingListDTO> getService() {
        return visitorWaitingListService;
    }

    @Override
    public Class<VisitorWaitingList> getEntityClass() {
        return VisitorWaitingList.class;
    }

    @PatchMapping("/setInProgress")
    public ResponseEntity<ResponseVisitorWaitingListDTO> setInProgress(@IdExists(entityClass = VisitorWaitingList.class, field = "visitKey") @RequestBody VisitKey id) {
        return ResponseEntity.ok(visitorWaitingListService.setInProgressById(id));
    }

    @PatchMapping("/setDone")
    public ResponseEntity<ResponseVisitorWaitingListDTO> setDone(@IdExists(entityClass = VisitorWaitingList.class, field = "visitKey") @RequestBody VisitKey id) {
        return ResponseEntity.ok(visitorWaitingListService.setDone(id));
    }

    @PatchMapping("/setCanceled")
    public ResponseEntity<ResponseVisitorWaitingListDTO> setCanceled(@IdExists(entityClass = VisitorWaitingList.class, field = "visitKey") @RequestBody VisitKey id) {
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

    @GetMapping("/orderByArrivalTime")
    public ResponseEntity<PageDTO<ResponseVisitorWaitingListDTO>> getAllOrderByArrivalTime(
            @PageableDefault(size = 10, sort = "arrivalTime", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(visitorWaitingListService.findAllOrderByArrivalTimeAsc(pageable));
    }

    @GetMapping("/orderByPriority")
    public ResponseEntity<PageDTO<ResponseVisitorWaitingListDTO>> getAllOrderByPriority(
            @PageableDefault(size = 10, sort = "priority", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(visitorWaitingListService.findAllOrderByPriorityAsc(pageable));
    }

    @GetMapping("/orderByEpt")
    public ResponseEntity<PageDTO<ResponseVisitorWaitingListDTO>> getAllOrderByEpt(
            @PageableDefault(size = 10, sort = "ept", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(visitorWaitingListService.findAllOrderByEptAsc(pageable));
    }
}
