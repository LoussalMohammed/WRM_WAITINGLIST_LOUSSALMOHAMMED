package org.wrmList.waitingList.visitor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wrmList.waitingList.common.controller.BaseController;
import org.wrmList.waitingList.common.service.BaseService;
import org.wrmList.waitingList.visitor.dto.request.CreateVisitorDTO;
import org.wrmList.waitingList.visitor.dto.response.ResponseVisitorDTO;
import org.wrmList.waitingList.visitor.dto.request.UpdateVisitorDTO;
import org.wrmList.waitingList.visitor.entity.Visitor;
import org.wrmList.waitingList.visitor.service.VisitorService;

// Visitor Controller

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
