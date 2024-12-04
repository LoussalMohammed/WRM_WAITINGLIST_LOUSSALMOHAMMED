package org.wrmList.waitingList.visitor.mapper.response;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.visitor.dto.request.UpdateVisitorDTO;
import org.wrmList.waitingList.visitor.entity.Visitor;

@Mapper(componentModel = "spring")
public interface UpdateVisitorMapper extends BaseMapper<Visitor, UpdateVisitorDTO> {
}
