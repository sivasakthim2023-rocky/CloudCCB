package com.cloudccb.backend.service;

import com.cloudccb.backend.entity.ChangeRequest;
import com.cloudccb.backend.entity.StatusHistory;
import com.cloudccb.backend.entity.User;
import com.cloudccb.backend.repository.ChangeRequestRepository;
import com.cloudccb.backend.repository.StatusHistoryRepository;
import com.cloudccb.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChangeRequestService {

    private final ChangeRequestRepository changeRequestRepository;
    private final UserRepository userRepository;
    private final StatusHistoryRepository statusHistoryRepository;

    public ChangeRequestService(
            ChangeRequestRepository changeRequestRepository,
            UserRepository userRepository,
            StatusHistoryRepository statusHistoryRepository) {

        this.changeRequestRepository = changeRequestRepository;
        this.userRepository = userRepository;
        this.statusHistoryRepository = statusHistoryRepository;
    }

    // Create a new change request
    public ChangeRequest createChangeRequest(
            ChangeRequest changeRequest) {

        if (changeRequest.getCreatedAt() == null) {
            changeRequest.setCreatedAt(LocalDateTime.now());
        }

        if (changeRequest.getStatus() == null ||
                changeRequest.getStatus().isBlank()) {
            changeRequest.setStatus("PENDING");
        }

        ChangeRequest savedRequest =
                changeRequestRepository.save(changeRequest);

        // Record initial status
        recordStatusHistory(
                savedRequest,
                savedRequest.getStatus(),
                "Change request created",
                savedRequest.getRequestedBy()
        );

        return savedRequest;
    }

    // Get all change requests
    public List<ChangeRequest> getAllChangeRequests() {

        return changeRequestRepository.findAll();
    }

    // Get change request by ID
    public Optional<ChangeRequest> getChangeRequestById(Long id) {

        return changeRequestRepository.findById(id);
    }

    // Get change requests by project
    public List<ChangeRequest> getChangeRequestsByProject(
            Long projectId) {

        return changeRequestRepository
                .findByProjectId(projectId);
    }

    // Get change requests by status
    public List<ChangeRequest> getChangeRequestsByStatus(
            String status) {

        return changeRequestRepository
                .findByStatus(status);
    }

    // Get change requests assigned to a developer
    public List<ChangeRequest> getChangeRequestsByDeveloper(
            Long userId) {

        return changeRequestRepository
                .findByAssignedToId(userId);
    }

    // Assign developer to a change request
    public Optional<ChangeRequest> assignDeveloper(
            Long changeRequestId,
            Long developerId) {

        Optional<ChangeRequest> requestOptional =
                changeRequestRepository.findById(changeRequestId);

        if (requestOptional.isEmpty()) {
            return Optional.empty();
        }

        ChangeRequest request = requestOptional.get();

        Optional<User> developerOptional =
                userRepository.findById(developerId);

        if (developerOptional.isEmpty()) {
            return Optional.empty();
        }

        User developer = developerOptional.get();

        request.setAssignedTo(developer);
        request.setStatus("ASSIGNED");
        request.setUpdatedAt(LocalDateTime.now());

        ChangeRequest savedRequest =
                changeRequestRepository.save(request);

        // Record assignment in status history
        recordStatusHistory(
                savedRequest,
                "ASSIGNED",
                "Developer assigned to change request",
                developer
        );

        return Optional.of(savedRequest);
    }

    // Update developer progress
    public Optional<ChangeRequest> updateProgress(
            Long changeRequestId,
            String status,
            String implementationNotes,
            String repositoryUrl,
            String gitCommitId,
            String branchName) {

        Optional<ChangeRequest> requestOptional =
                changeRequestRepository.findById(changeRequestId);

        if (requestOptional.isEmpty()) {
            return Optional.empty();
        }

        ChangeRequest request = requestOptional.get();

        request.setStatus(status.toUpperCase());
        request.setImplementationNotes(implementationNotes);
        request.setRepositoryUrl(repositoryUrl);
        request.setGitCommitId(gitCommitId);
        request.setBranchName(branchName);
        request.setUpdatedAt(LocalDateTime.now());

        if ("COMPLETED".equalsIgnoreCase(status)) {
            request.setCompletedAt(LocalDateTime.now());
        }

        ChangeRequest savedRequest =
                changeRequestRepository.save(request);

        // Record progress in status history
        recordStatusHistory(
                savedRequest,
                status.toUpperCase(),
                "Developer updated change request progress",
                savedRequest.getAssignedTo()
        );

        return Optional.of(savedRequest);
    }

    // Record status history
    private void recordStatusHistory(
            ChangeRequest changeRequest,
            String status,
            String comments,
            User changedBy) {

        if (status == null) {
            return;
        }

        StatusHistory history = new StatusHistory();

        history.setStatus(status.toUpperCase());
        history.setComments(comments);
        history.setChangedAt(LocalDateTime.now());
        history.setChangeRequest(changeRequest);
        history.setChangedBy(changedBy);

        statusHistoryRepository.save(history);
    }

    // Delete change request
    public void deleteChangeRequest(Long id) {

        changeRequestRepository.deleteById(id);
    }
}