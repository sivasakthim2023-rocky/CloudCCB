package com.cloudccb.backend.repository;

import com.cloudccb.backend.entity.ChangeApproval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChangeApprovalRepository
        extends JpaRepository<ChangeApproval, Long> {

    List<ChangeApproval> findByChangeRequestId(Long changeRequestId);

    List<ChangeApproval> findByApprovedById(Long userId);

    List<ChangeApproval> findByDecision(String decision);
}