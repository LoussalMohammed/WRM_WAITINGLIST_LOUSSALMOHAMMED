package org.wrmList.waitingList.visitor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wrmList.waitingList.common.controller.BaseController;
import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.visitor.dto.CreateVisitorDTO;
import org.wrmList.waitingList.visitor.dto.ResponseVisitorDTO;
import org.wrmList.waitingList.visitor.dto.UpdateVisitorDTO;
import org.wrmList.waitingList.visitor.entity.Visitor;
import org.wrmList.waitingList.visitor.service.VisitorService;

@RestController
@RequestMapping("/visitor")
@RequiredArgsConstructor
public class VisitorController extends BaseController<Visitor, Long, CreateVisitorDTO, UpdateVisitorDTO, ResponseVisitorDTO> {
    private final VisitorService visitorService;

    @Override
    protected BaseService<Long, CreateVisitorDTO, UpdateVisitorDTO, ResponseVisitorDTO> getService() {
        return visitorService;
    }

    @Override
    public Class<Visitor> getEntityClass() {
        return Visitor.class;
    }

    
}
