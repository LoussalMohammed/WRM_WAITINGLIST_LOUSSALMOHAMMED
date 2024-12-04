package org.wrmList.waitingList.visitorWaitingList.mapper.request;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.visitorWaitingList.dto.request.CreateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;

@Mapper(componentModel = "spring")
public interface CreateVisitorWaitingListMapper extends BaseMapper<VisitorWaitingList, CreateVisitorWaitingListDTO> {
}
