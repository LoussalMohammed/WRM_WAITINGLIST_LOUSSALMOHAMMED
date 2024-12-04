package org.wrmList.waitingList.visitorWaitingList.mapper.response;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.visitorWaitingList.dto.request.UpdateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;

@Mapper
public interface UpdateVisitorWaitingListMapper extends BaseMapper<VisitorWaitingList, UpdateVisitorWaitingListDTO> {
}
