package org.wrmList.waitingList.waitingList.service;

import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.waitingList.dto.CreateWaitingListDTO;
import org.wrmList.waitingList.waitingList.dto.ResponseWaitingListDTO;
import org.wrmList.waitingList.waitingList.dto.UpdateWaitingListDTO;

public interface WaitingListService extends BaseService<Long, CreateWaitingListDTO, UpdateWaitingListDTO, ResponseWaitingListDTO> {
}
