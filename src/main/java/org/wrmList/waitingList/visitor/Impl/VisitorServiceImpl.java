package org.wrmList.waitingList.visitor.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wrmList.waitingList.shared.dto.PageDTO;
import org.wrmList.waitingList.visitor.dto.ResponseVisitorDTO;
import org.wrmList.waitingList.visitor.entity.Visitor;
import org.wrmList.waitingList.visitor.dto.CreateVisitorDTO;
import org.wrmList.waitingList.visitor.dto.UpdateVisitorDTO;
import org.wrmList.waitingList.visitor.mapper.CreateVisitorMapper;
import org.wrmList.waitingList.visitor.mapper.ResponseVisitorMapper;
import org.wrmList.waitingList.visitor.repository.VisitorRepository;
import org.wrmList.waitingList.visitor.service.VisitorService;

@Service
@Slf4j
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {
    private final CreateVisitorMapper createMapper;
    private final ResponseVisitorMapper responseMapper;
    private final VisitorRepository visitorRepository;

    @Override
    public ResponseVisitorDTO create(CreateVisitorDTO requestDTO) {
        Visitor visitor = createMapper.toE(requestDTO);
        visitorRepository.save(visitor);
        return responseMapper.toOT(visitor);
    }

    @Override
    public PageDTO<ResponseVisitorDTO> findAll(Pageable pageable) {
        Page<Visitor> visitorPage = visitorRepository.findAll(pageable);
        Page<ResponseVisitorDTO> responsePage = visitorPage.map(responseMapper::toOT);
        return PageDTO.of(responsePage);
    }

    @Override
    public ResponseVisitorDTO findById(Long id) {
        Visitor visitor = visitorRepository.findByIdOrThrow(id);
        return responseMapper.toOT(visitor);
    }

    @Override
    public ResponseVisitorDTO updateById(UpdateVisitorDTO updateDTO, Long id) {
        Visitor visitor = visitorRepository.findByIdOrThrow(id);
        if(updateDTO.name() != null) {
            visitor.setName(updateDTO.name());
        }
        visitorRepository.save(visitor);
        return responseMapper.toOT(visitor);
    }

    @Override
    public void deleteById(Long id) {
        visitorRepository.deleteById(id);
    }
}
