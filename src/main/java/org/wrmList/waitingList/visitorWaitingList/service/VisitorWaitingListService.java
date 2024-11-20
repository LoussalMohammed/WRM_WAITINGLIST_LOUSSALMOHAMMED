package org.wrmList.waitingList.visitorWaitingList.service;

import org.springframework.data.domain.Pageable;
import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.shared.dto.PageDTO;
import org.wrmList.waitingList.visitorWaitingList.dto.CreateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.dto.ResponseVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.dto.UpdateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.entity.embeddable.VisitKey;
import org.wrmList.waitingList.waitingList.entity.WaitingList;

import java.time.LocalTime;
import java.util.List;


public interface VisitorWaitingListService extends BaseService<Long, CreateVisitorWaitingListDTO, UpdateVisitorWaitingListDTO, ResponseVisitorWaitingListDTO> {
    ResponseVisitorWaitingListDTO findById(VisitKey id);
    ResponseVisitorWaitingListDTO updateById(UpdateVisitorWaitingListDTO updateVisitorWaitingListDTO, VisitKey id);
    void deleteById(VisitKey id);
    ResponseVisitorWaitingListDTO setInProgressById(VisitKey id);
    ResponseVisitorWaitingListDTO setDone(VisitKey id);
    ResponseVisitorWaitingListDTO setCanceled(VisitKey id);
    LocalTime getAverageTime(Long id);
    List<ResponseVisitorWaitingListDTO> findByWaitingListId(Long id);
    PageDTO<ResponseVisitorWaitingListDTO> findAllOrderByArrivalTimeAsc(Pageable pageable);
    PageDTO<ResponseVisitorWaitingListDTO> findAllOrderByPriorityAsc(Pageable pageable);
    PageDTO<ResponseVisitorWaitingListDTO> findAllOrderByEptAsc(Pageable pageable);
}