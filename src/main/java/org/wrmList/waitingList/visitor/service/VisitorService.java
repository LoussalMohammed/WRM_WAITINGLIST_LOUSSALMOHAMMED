package org.wrmList.waitingList.visitor.service;

import org.springframework.stereotype.Service;
import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.visitor.dto.CreateVisitorDTO;
import org.wrmList.waitingList.visitor.dto.ResponseVisitorDTO;
import org.wrmList.waitingList.visitor.dto.UpdateVisitorDTO;

@Service
public interface VisitorService extends BaseService<Long, CreateVisitorDTO, UpdateVisitorDTO, ResponseVisitorDTO> {

}
