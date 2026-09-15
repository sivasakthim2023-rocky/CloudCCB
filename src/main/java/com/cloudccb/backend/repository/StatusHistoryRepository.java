package com.cloudccb.backend.repository;

import com.cloudccb.backend.entity.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusHistoryRepository
        extends JpaRepository<StatusHistory, Long> {

    List<StatusHistory> findByChangeRequestId(Long changeRequestId);

    List<StatusHistory> findByChangedById(Long userId);

    List<StatusHistory> findByStatus(String status);
}