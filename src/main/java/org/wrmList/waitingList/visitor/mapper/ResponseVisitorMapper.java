package org.wrmList.waitingList.visitor.mapper;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.visitor.dto.ResponseVisitorDTO;
import org.wrmList.waitingList.visitor.entity.Visitor;

@Mapper(componentModel = "spring")
public interface ResponseVisitorMapper extends BaseMapper<Visitor, ResponseVisitorDTO> {
}
