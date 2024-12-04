package org.wrmList.waitingList.waitingList.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wrmList.waitingList.config.AppConfig;
import org.wrmList.waitingList.shared.dto.response.PageResponse;
import org.wrmList.waitingList.util.enums.OrderingStrategy;
import org.wrmList.waitingList.waitingList.dto.request.CreateWaitingListDTO;
import org.wrmList.waitingList.waitingList.dto.response.ResponseWaitingListDTO;
import org.wrmList.waitingList.waitingList.dto.request.UpdateWaitingListDTO;
import org.wrmList.waitingList.waitingList.entity.WaitingList;
import org.wrmList.waitingList.waitingList.mapper.request.CreateWaitingListMapper;
import org.wrmList.waitingList.waitingList.mapper.response.ResponseWaitingListMapper;
import org.wrmList.waitingList.waitingList.repository.WaitingListRepository;
import org.wrmList.waitingList.waitingList.service.WaitingListService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class WaitingListServiceImpl implements WaitingListService {
    private final CreateWaitingListMapper createWaitingListMapper;
    private final ResponseWaitingListMapper responseWaitingListMapper;
    private final WaitingListRepository waitingListRepository;
    private final AppConfig config;

    @Override
    public ResponseWaitingListDTO create(CreateWaitingListDTO requestDTO) {
        int capacity = requestDTO.capacity() != 0 ? requestDTO.capacity() : config.getCapacity();
        OrderingStrategy orderingStrategy = requestDTO.orderingStrategy() != null ? requestDTO.orderingStrategy() : OrderingStrategy.valueOf(config.getOrderingStrategy());
        WaitingList waitingList = createWaitingListMapper.toE(requestDTO);
        waitingList.setCapacity(capacity);
        waitingList.setOrderingStrategy(orderingStrategy);
        waitingListRepository.save(waitingList);
        return responseWaitingListMapper.toOT(waitingList);
    }

    @Override
    public PageResponse<ResponseWaitingListDTO> findAll(Pageable pageable, Map<String, String> filters) {
        Page<WaitingList> page = waitingListRepository.findAll(pageable);
        List<ResponseWaitingListDTO> items = page.getContent()
                .stream()
                .map(responseWaitingListMapper::toOT)
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
    public ResponseWaitingListDTO findById(Long id) {
        WaitingList waitingList = waitingListRepository.findByIdOrThrow(id);
        return responseWaitingListMapper.toOT(waitingList);
    }

    @Override
    public ResponseWaitingListDTO updateById(UpdateWaitingListDTO updateWaitingListDTO, Long id) {
        WaitingList existingWaitingList = waitingListRepository.findByIdOrThrow(id);
        log.info("found existing waiting list: {}", existingWaitingList);
        if(updateWaitingListDTO.capacity() != existingWaitingList.getCapacity()) {
            existingWaitingList.setCapacity(updateWaitingListDTO.capacity());
        }
        log.info("updated capacity if not null: {}", existingWaitingList.getCapacity());
        if(updateWaitingListDTO.date() != null) {
            existingWaitingList.setDate(updateWaitingListDTO.date());
        }
        log.info("updated date if not null: {}", existingWaitingList.getDate());
        if(updateWaitingListDTO.orderingStrategy() != null) {
            existingWaitingList.setOrderingStrategy(updateWaitingListDTO.orderingStrategy());
        }
        log.info("updated operation days if not null: {}", existingWaitingList.getDate());
        if(updateWaitingListDTO.serviceTime() != null) {
            existingWaitingList.setServiceTime(updateWaitingListDTO.serviceTime());
        }
        log.info("updated ordering strategy if not null: {}", existingWaitingList.getOrderingStrategy());

        waitingListRepository.save(existingWaitingList);
        return responseWaitingListMapper.toOT(existingWaitingList);
    }

    @Override
    public void deleteById(Long id) {
        waitingListRepository.deleteById(id);
    }
}
