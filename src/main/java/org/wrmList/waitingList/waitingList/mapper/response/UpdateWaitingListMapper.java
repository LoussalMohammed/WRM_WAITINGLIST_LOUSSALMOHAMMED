package org.wrmList.waitingList.waitingList.mapper.response;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.waitingList.dto.request.UpdateWaitingListDTO;
import org.wrmList.waitingList.waitingList.entity.WaitingList;

@Mapper(componentModel = "spring")
public interface UpdateWaitingListMapper extends BaseMapper<WaitingList, UpdateWaitingListDTO> {
}
