package org.wrmList.waitingList.visitorWaitingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wrmList.waitingList.shared.exception.ResourceNotFoundException;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitorWaitingList;
import org.wrmList.waitingList.visitorWaitingList.entity.VisitKey;
import org.wrmList.waitingList.waitingList.entity.WaitingList;

import java.util.List;

// Visitor Waiting List Repository

@Repository
public interface VisitorWaitingListRepository extends JpaRepository<VisitorWaitingList, VisitKey> {
    default VisitorWaitingList findByIdOrThrow(VisitKey id) {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Visitor Waiting List Not Found Using Specified id: " + id));
    }


    List<VisitorWaitingList> findByWaitingListOrderByArrivalTimeAsc(WaitingList waitingList);
    List<VisitorWaitingList> findByWaitingListOrderByPriorityAsc(WaitingList waitingList);
    List<VisitorWaitingList> findByWaitingListOrderByEptAsc(WaitingList waitingList);

  }
