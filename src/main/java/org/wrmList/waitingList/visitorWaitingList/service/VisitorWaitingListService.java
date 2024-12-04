package org.wrmList.waitingList.visitorWaitingList.service;

import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.visitorWaitingList.dto.request.CreateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.dto.response.ResponseVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.dto.request.UpdateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitKey;

import java.time.LocalTime;
import java.util.List;


public interface VisitorWaitingListService extends BaseService<Long, CreateVisitorWaitingListDTO, UpdateVisitorWaitingListDTO, ResponseVisitorWaitingListDTO> {
    ResponseVisitorWaitingListDTO findById(VisitKey id);
    ResponseVisitorWaitingListDTO updateById(UpdateVisitorWaitingListDTO updateVisitorWaitingListDTO, Long id);
    ResponseVisitorWaitingListDTO updateById(UpdateVisitorWaitingListDTO updateVisitorWaitingListDTO);
    void deleteById(VisitKey id);
    ResponseVisitorWaitingListDTO setInProgressById(VisitKey id);
    ResponseVisitorWaitingListDTO setDone(VisitKey id);
    ResponseVisitorWaitingListDTO setCanceled(VisitKey id);
    LocalTime getAverageTime(Long id);
    List<ResponseVisitorWaitingListDTO> findByWaitingListId(Long id);
    }