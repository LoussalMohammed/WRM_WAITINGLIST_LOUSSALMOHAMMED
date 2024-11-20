package org.wrmList.waitingList.waitingList.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wrmList.waitingList.common.controller.BaseController;
import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.waitingList.dto.CreateWaitingListDTO;
import org.wrmList.waitingList.waitingList.dto.ResponseWaitingListDTO;
import org.wrmList.waitingList.waitingList.dto.UpdateWaitingListDTO;
import org.wrmList.waitingList.waitingList.entity.WaitingList;
import org.wrmList.waitingList.waitingList.service.WaitingListService;

@RestController
@RequestMapping("/waitingList")
@RequiredArgsConstructor
@Component
public class WaitingListController extends BaseController<WaitingList, Long, CreateWaitingListDTO, UpdateWaitingListDTO, ResponseWaitingListDTO> {
    private final WaitingListService waitingListService;

    @Override
    protected BaseService<Long, CreateWaitingListDTO, UpdateWaitingListDTO, ResponseWaitingListDTO> getService() {
        return waitingListService;
    }
    @Override
    public Class<WaitingList> getEntityClass() {
        return WaitingList.class;
    }
}
