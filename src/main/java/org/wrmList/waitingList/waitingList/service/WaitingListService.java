package org.wrmList.waitingList.waitingList.service;

import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.waitingList.dto.request.CreateWaitingListDTO;
import org.wrmList.waitingList.waitingList.dto.response.ResponseWaitingListDTO;
import org.wrmList.waitingList.waitingList.dto.request.UpdateWaitingListDTO;

public interface WaitingListService extends BaseService<Long, CreateWaitingListDTO, UpdateWaitingListDTO, ResponseWaitingListDTO> {
}
