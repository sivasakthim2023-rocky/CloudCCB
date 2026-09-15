package com.cloudccb.backend.repository;

import com.cloudccb.backend.entity.ChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChangeRequestRepository
        extends JpaRepository<ChangeRequest, Long> {

    List<ChangeRequest> findByProjectId(Long projectId);

    List<ChangeRequest> findByStatus(String status);

    List<ChangeRequest> findByAssignedToId(Long userId);
}