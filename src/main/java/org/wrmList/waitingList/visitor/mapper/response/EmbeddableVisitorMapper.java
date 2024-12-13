package org.wrmList.waitingList.visitor.mapper.response;

import org.mapstruct.Mapper;
import org.wrmList.waitingList.common.mapper.BaseMapper;
import org.wrmList.waitingList.visitor.dto.response.EmbeddableVisitorDTO;
import org.wrmList.waitingList.visitor.entity.Visitor;


// Embeddable Visitor Mapper

@Mapper(componentModel = "spring")
public interface EmbeddableVisitorMapper extends BaseMapper<Visitor, EmbeddableVisitorDTO> {
}
