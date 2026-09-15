package com.cloudccb.backend.controller;

import com.cloudccb.backend.entity.ChangeRequest;
import com.cloudccb.backend.service.ChangeRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/change-requests")
@CrossOrigin(origins = "http://localhost:3000")
public class ChangeRequestController {

    private final ChangeRequestService changeRequestService;

    public ChangeRequestController(
            ChangeRequestService changeRequestService) {

        this.changeRequestService = changeRequestService;
    }

    // Create a new change request
    @PostMapping
    public ResponseEntity<ChangeRequest> createChangeRequest(
            @RequestBody ChangeRequest changeRequest) {

        return ResponseEntity.ok(
                changeRequestService.createChangeRequest(
                        changeRequest
                )
        );
    }

    // Get all change requests
    @GetMapping
    public ResponseEntity<List<ChangeRequest>> getAllChangeRequests() {

        return ResponseEntity.ok(
                changeRequestService.getAllChangeRequests()
        );
    }

    // Get change request by ID
    @GetMapping("/{id}")
    public ResponseEntity<ChangeRequest> getChangeRequestById(
            @PathVariable Long id) {

        return changeRequestService
                .getChangeRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get change requests by project
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ChangeRequest>> getByProject(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                changeRequestService
                        .getChangeRequestsByProject(projectId)
        );
    }

    // Get change requests by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ChangeRequest>> getByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                changeRequestService
                        .getChangeRequestsByStatus(status)
        );
    }

    // Get change requests assigned to a developer
    @GetMapping("/developer/{userId}")
    public ResponseEntity<List<ChangeRequest>> getByDeveloper(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                changeRequestService
                        .getChangeRequestsByDeveloper(userId)
        );
    }

    // Assign developer to a change request
    @PutMapping("/{changeRequestId}/assign/{developerId}")
    public ResponseEntity<ChangeRequest> assignDeveloper(
            @PathVariable Long changeRequestId,
            @PathVariable Long developerId) {

        return changeRequestService
                .assignDeveloper(
                        changeRequestId,
                        developerId
                )
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update developer progress
    @PutMapping("/{changeRequestId}/progress")
    public ResponseEntity<ChangeRequest> updateProgress(
            @PathVariable Long changeRequestId,
            @RequestParam String status,
            @RequestParam(required = false)
            String implementationNotes,
            @RequestParam(required = false)
            String repositoryUrl,
            @RequestParam(required = false)
            String gitCommitId,
            @RequestParam(required = false)
            String branchName) {

        return changeRequestService
                .updateProgress(
                        changeRequestId,
                        status,
                        implementationNotes,
                        repositoryUrl,
                        gitCommitId,
                        branchName
                )
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete change request
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChangeRequest(
            @PathVariable Long id) {

        changeRequestService.deleteChangeRequest(id);

        return ResponseEntity.noContent().build();
    }
}