package org.wrmList.waitingList.visitorWaitingList.mapper;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.visitorWaitingList.dto.CreateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;

@Mapper(componentModel = "spring")
public interface CreateVisitorWaitingListMapper extends BaseMapper<VisitorWaitingList, CreateVisitorWaitingListDTO> {
}
