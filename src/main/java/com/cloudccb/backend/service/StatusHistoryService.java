package com.cloudccb.backend.service;

import com.cloudccb.backend.entity.StatusHistory;
import com.cloudccb.backend.repository.StatusHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StatusHistoryService {

    private final StatusHistoryRepository statusHistoryRepository;

    public StatusHistoryService(
            StatusHistoryRepository statusHistoryRepository) {

        this.statusHistoryRepository = statusHistoryRepository;
    }

    // Create a status history record
    public StatusHistory createStatusHistory(
            StatusHistory statusHistory) {

        if (statusHistory.getChangedAt() == null) {
            statusHistory.setChangedAt(LocalDateTime.now());
        }

        return statusHistoryRepository.save(statusHistory);
    }

    // Get all status history records
    public List<StatusHistory> getAllStatusHistory() {

        return statusHistoryRepository.findAll();
    }

    // Get status history by ID
    public Optional<StatusHistory> getStatusHistoryById(Long id) {

        return statusHistoryRepository.findById(id);
    }

    // Get history for a particular change request
    public List<StatusHistory> getHistoryByChangeRequest(
            Long changeRequestId) {

        return statusHistoryRepository
                .findByChangeRequestId(changeRequestId);
    }

    // Get history created by a particular user
    public List<StatusHistory> getHistoryByUser(Long userId) {

        return statusHistoryRepository
                .findByChangedById(userId);
    }

    // Get history by status
    public List<StatusHistory> getHistoryByStatus(
            String status) {

        return statusHistoryRepository
                .findByStatus(status);
    }

    // Delete a status history record
    public void deleteStatusHistory(Long id) {

        statusHistoryRepository.deleteById(id);
    }
}