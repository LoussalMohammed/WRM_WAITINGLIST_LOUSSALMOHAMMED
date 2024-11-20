package org.wrmList.waitingList.waitingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wrmList.waitingList.shared.exception.ResourceNotFoundException;
import org.wrmList.waitingList.waitingList.entity.WaitingList;

public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {
    default WaitingList findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Waiting List Not Found Using Specified id: {}"));
    }
}
