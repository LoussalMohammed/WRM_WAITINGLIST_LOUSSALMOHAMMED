package org.wrmList.waitingList.waitingList.mapper;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.waitingList.dto.CreateWaitingListDTO;
import org.wrmList.waitingList.waitingList.entity.WaitingList;

@Mapper(componentModel = "spring")
public interface CreateWaitingListMapper extends BaseMapper<WaitingList, CreateWaitingListDTO> {

}
