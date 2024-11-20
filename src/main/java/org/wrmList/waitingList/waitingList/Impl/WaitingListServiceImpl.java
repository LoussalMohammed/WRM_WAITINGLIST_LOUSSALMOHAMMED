package org.wrmList.waitingList.waitingList.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wrmList.waitingList.shared.dto.PageDTO;
import org.wrmList.waitingList.waitingList.dto.CreateWaitingListDTO;
import org.wrmList.waitingList.waitingList.dto.ResponseWaitingListDTO;
import org.wrmList.waitingList.waitingList.dto.UpdateWaitingListDTO;
import org.wrmList.waitingList.waitingList.entity.WaitingList;
import org.wrmList.waitingList.waitingList.mapper.CreateWaitingListMapper;
import org.wrmList.waitingList.waitingList.mapper.ResponseWaitingListMapper;
import org.wrmList.waitingList.waitingList.repository.WaitingListRepository;
import org.wrmList.waitingList.waitingList.service.WaitingListService;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class WaitingListServiceImpl implements WaitingListService {
    private final CreateWaitingListMapper createWaitingListMapper;
    private final ResponseWaitingListMapper responseWaitingListMapper;
    private final WaitingListRepository waitingListRepository;

    @Override
    public ResponseWaitingListDTO create(CreateWaitingListDTO requestDTO) {
        WaitingList waitingList = createWaitingListMapper.toE(requestDTO);
        waitingListRepository.save(waitingList);
        return responseWaitingListMapper.toOT(waitingList);
    }

    @Override
    public PageDTO<ResponseWaitingListDTO> findAll(Pageable pageable) {
        Page<WaitingList> waitingListPage = waitingListRepository.findAll(pageable);
        Page<ResponseWaitingListDTO> responsePage = waitingListPage.map(responseWaitingListMapper::toOT);
        return PageDTO.of(responsePage);
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
        try {
            waitingListRepository.save(existingWaitingList);
            log.info("saved transaction");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new RuntimeException("Exception Message: "+illegalArgumentException.getMessage()+"\nException cause: "+illegalArgumentException.getCause());
        }
        return responseWaitingListMapper.toOT(existingWaitingList);
    }

    @Override
    public void deleteById(Long id) {
        waitingListRepository.deleteById(id);
    }
}
