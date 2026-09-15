package com.cloudccb.backend.controller;

import com.cloudccb.backend.entity.StatusHistory;
import com.cloudccb.backend.service.StatusHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status-history")
@CrossOrigin(origins = "http://localhost:3000")
public class StatusHistoryController {

    private final StatusHistoryService statusHistoryService;

    public StatusHistoryController(
            StatusHistoryService statusHistoryService) {

        this.statusHistoryService = statusHistoryService;
    }

    // Create a status history record
    @PostMapping
    public ResponseEntity<StatusHistory> createStatusHistory(
            @RequestBody StatusHistory statusHistory) {

        return ResponseEntity.ok(
                statusHistoryService.createStatusHistory(statusHistory)
        );
    }

    // Get all status history
    @GetMapping
    public ResponseEntity<List<StatusHistory>> getAllStatusHistory() {

        return ResponseEntity.ok(
                statusHistoryService.getAllStatusHistory()
        );
    }

    // Get status history by ID
    @GetMapping("/{id}")
    public ResponseEntity<StatusHistory> getStatusHistoryById(
            @PathVariable Long id) {

        return statusHistoryService.getStatusHistoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get history for a change request
    @GetMapping("/change-request/{changeRequestId}")
    public ResponseEntity<List<StatusHistory>>
    getHistoryByChangeRequest(
            @PathVariable Long changeRequestId) {

        return ResponseEntity.ok(
                statusHistoryService
                        .getHistoryByChangeRequest(changeRequestId)
        );
    }

    // Get history created by a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StatusHistory>>
    getHistoryByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                statusHistoryService
                        .getHistoryByUser(userId)
        );
    }

    // Get history by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<StatusHistory>>
    getHistoryByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                statusHistoryService
                        .getHistoryByStatus(status)
        );
    }

    // Delete status history
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatusHistory(
            @PathVariable Long id) {

        statusHistoryService.deleteStatusHistory(id);

        return ResponseEntity.noContent().build();
    }
}