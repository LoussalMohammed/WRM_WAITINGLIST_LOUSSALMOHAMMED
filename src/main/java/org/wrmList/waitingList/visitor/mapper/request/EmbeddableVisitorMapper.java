package org.wrmList.waitingList.visitor.mapper.request;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.visitor.dto.response.EmbeddableVisitorDTO;
import org.wrmList.waitingList.visitor.entity.Visitor;

@Mapper(componentModel = "spring")
public interface EmbeddableVisitorMapper extends BaseMapper<Visitor, EmbeddableVisitorDTO> {
}
