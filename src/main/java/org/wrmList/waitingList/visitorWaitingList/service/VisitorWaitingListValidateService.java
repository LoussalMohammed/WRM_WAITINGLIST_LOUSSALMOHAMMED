package org.wrmList.waitingList.visitorWaitingList.service;

import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;

import java.util.List;

public interface VisitorWaitingListValidateService {
    Integer countVisits(List<VisitorWaitingList> visitorWaitingLists);
    void isValidVisit(VisitorWaitingList visitorWaitingList);
    void isValidTime(VisitorWaitingList visitorWaitingList);
    void isValidDate(VisitorWaitingList visitorWaitingList);
    void isValidTimeDifference(VisitorWaitingList visitorWaitingList);
    void createValidate(VisitorWaitingList visitorWaitingList);
    void updateValidate(VisitorWaitingList visitorWaitingList);

}
