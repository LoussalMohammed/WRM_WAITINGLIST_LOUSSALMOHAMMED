package org.wrmList.waitingList.visitorWaitingList.mapper.response;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.visitorWaitingList.dto.response.ResponseVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;

@Mapper(componentModel = "spring")
public interface ResponseVisitorWaitingListMapper extends BaseMapper<VisitorWaitingList, ResponseVisitorWaitingListDTO> {
}
