package org.wrmList.waitingList.visitorWaitingList.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.wrmList.waitingList.shared.exception.InvalidDataException;
import org.wrmList.waitingList.util.enums.ServiceTime;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;
import org.wrmList.waitingList.visitorWaitingList.repository.VisitorWaitingListRepository;
import org.wrmList.waitingList.visitorWaitingList.service.VisitorWaitingListValidateService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VisitorWaitingListValidateServiceImpl implements VisitorWaitingListValidateService {
    private final VisitorWaitingListRepository visitorWaitingListRepository;

    List<VisitorWaitingList> getVisitorsWaitingLists(VisitorWaitingList visitorWaitingList) {
        return visitorWaitingListRepository.findByWaitingListOrderByArrivalTimeAsc(visitorWaitingList.getWaitingList());
    }

    int getCapacity(VisitorWaitingList visitorWaitingList) {
        return visitorWaitingList.getWaitingList().getCapacity();
    }

    @Override
    public Integer countVisits(List<VisitorWaitingList> visitorWaitingLists) {
        return  visitorWaitingLists
                .size();
    }

    @Override
    public void isValidVisit(VisitorWaitingList visitorWaitingList) {
        List<VisitorWaitingList> visitorWaitingLists = getVisitorsWaitingLists(visitorWaitingList);
        int capacity = getCapacity(visitorWaitingList);
        if(capacity <= countVisits(visitorWaitingLists)) {
            throw new InvalidDataException("can't add new visit, out of waiting list capacity!");
        }

        isValidDate(visitorWaitingList);
        isValidTime(visitorWaitingList);
        if(visitorWaitingList.getStartTime() != null && visitorWaitingList.getEndTime() != null) {
            isValidTimeDifference(visitorWaitingList);
        }
    }

    @Override
    public void isValidTime(VisitorWaitingList visitorWaitingList) {
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

    @Override
    public void isValidDate(VisitorWaitingList visitorWaitingList) {
        LocalDate waitingListDate = visitorWaitingList.getWaitingList().getDate();
        LocalDate visitorWaitingListDate = visitorWaitingList.getArrivalTime().toLocalDate();

        if(visitorWaitingListDate.isBefore(waitingListDate)) {
            throw new InvalidDataException("Arrival date is not allowed!");
        }
    }

    @Override
    public void isValidTimeDifference(VisitorWaitingList visitorWaitingList) {
            log.info("started validating");
            log.info("arrival time:{} start time:{} end time:{}", visitorWaitingList.getArrivalTime().toLocalTime(), visitorWaitingList.getStartTime(), visitorWaitingList.getEndTime());
            if(visitorWaitingList.getArrivalTime() != null && visitorWaitingList.getStartTime() != null && visitorWaitingList.getEndTime() != null) {
                if(!visitorWaitingList.getArrivalTime().toLocalTime().isBefore(visitorWaitingList.getStartTime()) && !visitorWaitingList.getEndTime().isAfter(visitorWaitingList.getStartTime())) {
                    log.info("started throwing");
                    throw new InvalidDataException("arrival time should not surpass start time, and start time should not surpass end time!");
                }
            }

    }

    @Override
    public void createValidate(VisitorWaitingList visitorWaitingList) {
        isValidVisit(visitorWaitingList);
    }

    @Override
    public void updateValidate(VisitorWaitingList visitorWaitingList) {
        isValidTimeDifference(visitorWaitingList);
    }
}
