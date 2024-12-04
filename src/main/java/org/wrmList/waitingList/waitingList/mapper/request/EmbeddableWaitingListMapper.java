package org.wrmList.waitingList.waitingList.mapper.request;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.waitingList.dto.response.EmbeddableWaitingListDTO;
import org.wrmList.waitingList.waitingList.entity.WaitingList;

@Mapper(componentModel = "spring")
public interface EmbeddableWaitingListMapper extends BaseMapper<WaitingList, EmbeddableWaitingListDTO> {
}
