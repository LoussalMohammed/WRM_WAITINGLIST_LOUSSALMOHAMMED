package org.wrmList.waitingList.visitor.mapper.request;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.visitor.entity.Visitor;
import org.wrmList.waitingList.visitor.dto.request.CreateVisitorDTO;

@Mapper(componentModel = "spring")
public interface CreateVisitorMapper extends BaseMapper<Visitor, CreateVisitorDTO> {
}
