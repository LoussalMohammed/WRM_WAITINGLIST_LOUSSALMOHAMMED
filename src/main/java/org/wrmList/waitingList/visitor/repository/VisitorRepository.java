package org.wrmList.waitingList.visitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wrmList.waitingList.shared.exception.ResourceNotFoundException;
import org.wrmList.waitingList.visitor.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    default Visitor findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Visitor Not Found Using Specified id: {}"));
    }
}
