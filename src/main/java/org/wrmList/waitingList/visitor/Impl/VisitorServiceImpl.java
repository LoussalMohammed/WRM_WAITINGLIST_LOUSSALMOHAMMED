package org.wrmList.waitingList.visitor.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wrmList.waitingList.shared.dto.response.PageResponse;
import org.wrmList.waitingList.visitor.dto.response.ResponseVisitorDTO;
import org.wrmList.waitingList.visitor.entity.Visitor;
import org.wrmList.waitingList.visitor.dto.request.CreateVisitorDTO;
import org.wrmList.waitingList.visitor.dto.request.UpdateVisitorDTO;
import org.wrmList.waitingList.visitor.mapper.request.CreateVisitorMapper;
import org.wrmList.waitingList.visitor.mapper.response.ResponseVisitorMapper;
import org.wrmList.waitingList.visitor.repository.VisitorRepository;
import org.wrmList.waitingList.visitor.service.VisitorService;

import java.util.List;
import java.util.Map;

// Visitor Service Impl

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
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
    public PageResponse<ResponseVisitorDTO> findAll(Pageable pageable, Map<String, String> filters) {
        Page<Visitor> page = visitorRepository.findAll(pageable);
        List<ResponseVisitorDTO> items = page.getContent()
                .stream()
                .map(responseMapper::toOT)
                .toList();
        return PageResponse.of(
                items,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
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
