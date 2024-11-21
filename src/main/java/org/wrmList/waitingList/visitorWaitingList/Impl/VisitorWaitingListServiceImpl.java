package org.wrmList.waitingList.visitorWaitingList.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.wrmList.waitingList.shared.dto.response.PageResponse;
import org.wrmList.waitingList.shared.exception.InvalidDataException;
import org.wrmList.waitingList.util.enums.ServiceTime;
import org.wrmList.waitingList.util.enums.VisitorStatus;
import org.wrmList.waitingList.visitor.entity.Visitor;
import org.wrmList.waitingList.visitor.repository.VisitorRepository;
import org.wrmList.waitingList.visitorWaitingList.dto.CreateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.dto.ResponseVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.dto.UpdateVisitorWaitingListDTO;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;
import org.wrmList.waitingList.visitorWaitingList.entity.embeddable.VisitKey;
import org.wrmList.waitingList.visitorWaitingList.mapper.CreateVisitorWaitingListMapper;
import org.wrmList.waitingList.visitorWaitingList.mapper.ResponseVisitorWaitingListMapper;
import org.wrmList.waitingList.visitorWaitingList.repository.VisitorWaitingListRepository;
import org.wrmList.waitingList.visitorWaitingList.service.VisitorWaitingListService;
import org.wrmList.waitingList.waitingList.entity.WaitingList;
import org.wrmList.waitingList.waitingList.repository.WaitingListRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;


@Service
@Validated
@RequiredArgsConstructor
@Slf4j
@Transactional
public class VisitorWaitingListServiceImpl implements VisitorWaitingListService {
    private final CreateVisitorWaitingListMapper createMapper;
    private final ResponseVisitorWaitingListMapper responseMapper;
    private final VisitorWaitingListRepository visitorWaitingListRepository;
    private final VisitorRepository visitorRepository;
    private final WaitingListRepository waitingListRepository;

    Integer countVisits(List<VisitorWaitingList> visitorWaitingLists) {
        return  visitorWaitingLists
                .size();
    }

    void isValidVisit(VisitorWaitingList visitorWaitingList) {
        List<VisitorWaitingList> visitorWaitingLists = visitorWaitingListRepository.findByWaitingListOrderByArrivalTimeAsc(visitorWaitingList.getWaitingList());
        int capacity = visitorWaitingList.getWaitingList().getCapacity();
        if(capacity <= countVisits(visitorWaitingLists)) {
            throw new InvalidDataException("can't add new visit, out of waiting list capacity!");
        }

        isValidDate(visitorWaitingList);
        isValidTime(visitorWaitingList);
        isValidTimeDifference(visitorWaitingList);

    }

    void isValidTime(VisitorWaitingList visitorWaitingList) {
        ServiceTime serviceTime = visitorWaitingList.getWaitingList().getServiceTime();
        LocalTime arrivalTime = visitorWaitingList.getArrivalTime().toLocalTime();
        String message = "Arrival time is not allowed!";
        switch (serviceTime) {
            case MORNING -> {
                if( arrivalTime.isBefore(LocalTime.of(8, 30)) | arrivalTime.isAfter(LocalTime.of(12, 30))) {
                    throw new InvalidDataException(message);
                }

            }

            case AFTERNOON -> {
                if( arrivalTime.isBefore(LocalTime.of(14, 30)) | arrivalTime.isAfter(LocalTime.of(18, 30))) {
                    throw new InvalidDataException(message);
                }

            }

            case CONTINUOUSLY -> {
                if( arrivalTime.isBefore(LocalTime.of(8, 30)) | arrivalTime.isAfter(LocalTime.of(18, 30)) ) {
                    throw new InvalidDataException(message);
                }
            }
        }

    }

    void isValidDate(VisitorWaitingList visitorWaitingList) {
        LocalDate waitingListDate = visitorWaitingList.getWaitingList().getDate();
        LocalDate visitorWaitingListDate = visitorWaitingList.getArrivalTime().toLocalDate();

        if(!visitorWaitingListDate.isEqual(waitingListDate)) {
            throw new InvalidDataException("Arrival date is not allowed!");
        }
    }

    void isValidTimeDifference(VisitorWaitingList visitorWaitingList) {
        if(!visitorWaitingList.getArrivalTime().toLocalTime().isBefore(visitorWaitingList.getStartTime()) && !visitorWaitingList.getEndTime().isAfter(visitorWaitingList.getStartTime())) {
            throw new InvalidDataException("arrival time should not surpass start time, and start time should not surpass end time!");
        }
    }

    @Override
    public ResponseVisitorWaitingListDTO create(CreateVisitorWaitingListDTO requestDTO) {
        VisitorWaitingList visitorWaitingList = createMapper.toE(requestDTO);
    
        Visitor visitor = visitorRepository.findByIdOrThrow(requestDTO.visitorId());
        WaitingList waitingList = waitingListRepository.findByIdOrThrow(requestDTO.waitingListId());
    
        VisitKey visitKey = new VisitKey(visitor.getId(), waitingList.getId());
        visitorWaitingList.setId(visitKey);
    
        visitorWaitingList.setVisitor(visitor);
        visitorWaitingList.setWaitingList(waitingList);

        isValidVisit(visitorWaitingList);

        visitorWaitingListRepository.save(visitorWaitingList);
            return responseMapper.toOT(visitorWaitingList);
    }

    @Override
    public PageResponse<ResponseVisitorWaitingListDTO> findAll(Pageable pageable, Map<String, String> filters) {
        Page<VisitorWaitingList> page = visitorWaitingListRepository.findAll(pageable);
        List<ResponseVisitorWaitingListDTO> items = page.getContent()
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
    public ResponseVisitorWaitingListDTO findById(Long id) {
        return null;
    }

    @Override
    public ResponseVisitorWaitingListDTO findById(VisitKey id) {
        VisitorWaitingList visitorWaitingList = visitorWaitingListRepository.findByIdOrThrow(id);
        return responseMapper.toOT(visitorWaitingList);
    }

    @Override
    public ResponseVisitorWaitingListDTO updateById(UpdateVisitorWaitingListDTO updateVisitorWaitingListDTO, Long id) {
        return null;
    }

    @Override
    public ResponseVisitorWaitingListDTO updateById(UpdateVisitorWaitingListDTO updateDTO) {
        VisitKey visitKey = new VisitKey(updateDTO.visitorId(), updateDTO.waitingListId());
        VisitorWaitingList visitorWaitingList = visitorWaitingListRepository.findByIdOrThrow(visitKey);
        if(updateDTO.arrivalTime() != null) {
            visitorWaitingList.setArrivalTime(updateDTO.arrivalTime());
        }
        if(updateDTO.visitorStatus() != null) {
            visitorWaitingList.setVisitorStatus(updateDTO.visitorStatus());
        }
        if(updateDTO.startTime() != null) {
            visitorWaitingList.setStartTime(updateDTO.startTime());
        }
        if(updateDTO.endTime() != null) {
            visitorWaitingList.setEndTime(updateDTO.endTime());
        }
        if(updateDTO.visitorId() != null) {
            Visitor visitor = visitorRepository.findByIdOrThrow(updateDTO.visitorId());
            visitorWaitingList.setVisitor(visitor);
        }
        if(visitorWaitingList.getPriority() != null && updateDTO.priority() != null) {
            visitorWaitingList.setPriority(updateDTO.priority());
        }
        if(visitorWaitingList.getEpt() != null && updateDTO.ept() != null) {
            visitorWaitingList.setEpt(updateDTO.ept());
        }

        isValidTimeDifference(visitorWaitingList);
        visitorWaitingListRepository.save(visitorWaitingList);
        return responseMapper.toOT(visitorWaitingList);
    }

    @Override
    public void deleteById(Long id) {

    }


    @Override
    public void deleteById(VisitKey id) {
        visitorWaitingListRepository.deleteById(id);
    }

    @Override
    public ResponseVisitorWaitingListDTO setInProgressById(VisitKey id) {
        VisitorWaitingList visitorWaitingList = visitorWaitingListRepository.findByIdOrThrow(id);
        if(visitorWaitingList.getVisitorStatus() != VisitorStatus.IN_PROGRESS && visitorWaitingList.getVisitorStatus() != VisitorStatus.DONE && visitorWaitingList.getVisitorStatus() != VisitorStatus.CANCELED) {
            visitorWaitingList.setVisitorStatus(VisitorStatus.IN_PROGRESS);
            visitorWaitingList.setStartTime(LocalTime.now());
        } else {
            throw new InvalidDataException("Can't set visitor status to In Progress, already: "+ visitorWaitingList.getVisitorStatus());
        }
        return responseMapper.toOT(visitorWaitingList);
    }

    @Override
    public ResponseVisitorWaitingListDTO setDone(VisitKey id) {
        VisitorWaitingList visitorWaitingList = visitorWaitingListRepository.findByIdOrThrow(id);
        if(visitorWaitingList.getVisitorStatus() != VisitorStatus.WAITING && visitorWaitingList.getVisitorStatus() !=  VisitorStatus.DONE && visitorWaitingList.getVisitorStatus() != VisitorStatus.CANCELED) {
            visitorWaitingList.setVisitorStatus(VisitorStatus.DONE);
            visitorWaitingList.setEndTime(LocalTime.now());
        } else {
            throw new InvalidDataException("Can't set visitor status to Done, Because current status is: "+ visitorWaitingList.getVisitorStatus());
        }

        return responseMapper.toOT(visitorWaitingList);
    }

    @Override
    public ResponseVisitorWaitingListDTO setCanceled(VisitKey id) {
        VisitorWaitingList visitorWaitingList = visitorWaitingListRepository.findByIdOrThrow(id);
        if(visitorWaitingList.getVisitorStatus() != VisitorStatus.CANCELED && visitorWaitingList.getVisitorStatus() != VisitorStatus.DONE && visitorWaitingList.getVisitorStatus() != VisitorStatus.IN_PROGRESS) {
            visitorWaitingList.setVisitorStatus(VisitorStatus.CANCELED);
        } else {
            throw new InvalidDataException("Can't set visitor status to Canceled, already: "+ visitorWaitingList.getVisitorStatus());
        }

        return responseMapper.toOT(visitorWaitingList);
    }

    @Override
    public LocalTime getAverageTime(Long id) {
        WaitingList waitingList = waitingListRepository.findByIdOrThrow(id);
        List<VisitorWaitingList> visitorWaitingLists = visitorWaitingListRepository.findByWaitingListOrderByArrivalTimeAsc(waitingList);
        List<VisitorWaitingList> startedVisitorWaitingLists = visitorWaitingLists
                .stream()
                .filter(visitorWaitingList -> visitorWaitingList.getStartTime() != null)
                .toList();

        if (startedVisitorWaitingLists.isEmpty()) {
            log.info("no visits are complete!");
            return LocalTime.of(0, 0); // Return 00:00 if no completed visits
        }

        double averageSeconds = startedVisitorWaitingLists
                .stream()
                .mapToLong(visit -> Duration.between(visit.getArrivalTime().toLocalTime(), visit.getStartTime()).getSeconds())
                .average()
                .orElse(0.0);

        long hours = (long) (averageSeconds / 3600);
        long minutes = (long) ((averageSeconds % 3600) / 60);
        long seconds = (long) (averageSeconds % 60);

        return LocalTime.of((int) hours, (int) minutes, (int) seconds);
    }

    @Override
    public List<ResponseVisitorWaitingListDTO> findByWaitingListId(Long id) {
        WaitingList waitingList = waitingListRepository.findByIdOrThrow(id);
        List<VisitorWaitingList> visitorWaitingLists = null;
        switch (waitingList.getOrderingStrategy()) {
            case FIFO -> {
                visitorWaitingLists = visitorWaitingListRepository.findByWaitingListOrderByArrivalTimeAsc(waitingList);
            }
            case HPF -> {
                visitorWaitingLists = visitorWaitingListRepository.findByWaitingListOrderByPriorityAsc(waitingList);
            }
            case STF -> {
                visitorWaitingLists = visitorWaitingListRepository.findByWaitingListOrderByEptAsc(waitingList);
            }
        }

        if(visitorWaitingLists == null) {
            visitorWaitingLists = visitorWaitingListRepository.findByWaitingListOrderByArrivalTimeAsc(waitingList);
        }

        return visitorWaitingLists
                .stream()
                .map(responseMapper::toOT)
                .toList();
    }

}
