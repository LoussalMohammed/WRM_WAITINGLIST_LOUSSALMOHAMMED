package org.wrmList.waitingList.visitorWaitingList.mapper;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.visitorWaitingList.dto.UpdateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;

@Mapper
public interface UpdateVisitorWaitingListMapper extends BaseMapper<VisitorWaitingList, UpdateVisitorWaitingListDTO> {
}
