package org.wrmList.waitingList.visitor.mapper.request;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.visitor.dto.request.UpdateVisitorDTO;
import org.wrmList.waitingList.visitor.entity.Visitor;

// Update Visitor Mapper

@Mapper(componentModel = "spring")
public interface UpdateVisitorMapper extends BaseMapper<Visitor, UpdateVisitorDTO> {
}
