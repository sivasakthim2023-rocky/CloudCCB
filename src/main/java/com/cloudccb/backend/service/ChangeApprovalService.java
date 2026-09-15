package com.cloudccb.backend.service;

import com.cloudccb.backend.entity.ChangeApproval;
import com.cloudccb.backend.entity.ChangeRequest;
import com.cloudccb.backend.entity.StatusHistory;
import com.cloudccb.backend.repository.ChangeApprovalRepository;
import com.cloudccb.backend.repository.ChangeRequestRepository;
import com.cloudccb.backend.repository.StatusHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChangeApprovalService {

    private final ChangeApprovalRepository changeApprovalRepository;
    private final ChangeRequestRepository changeRequestRepository;
    private final StatusHistoryRepository statusHistoryRepository;

    public ChangeApprovalService(
            ChangeApprovalRepository changeApprovalRepository,
            ChangeRequestRepository changeRequestRepository,
            StatusHistoryRepository statusHistoryRepository) {

        this.changeApprovalRepository = changeApprovalRepository;
        this.changeRequestRepository = changeRequestRepository;
        this.statusHistoryRepository = statusHistoryRepository;
    }

    // Create CCB approval
    public ChangeApproval createApproval(ChangeApproval approval) {

        approval.setDecidedAt(LocalDateTime.now());

        ChangeApproval savedApproval =
                changeApprovalRepository.save(approval);

        ChangeRequest changeRequest =
                approval.getChangeRequest();

        if (changeRequest != null &&
                changeRequest.getId() != null) {

            Optional<ChangeRequest> existingRequest =
                    changeRequestRepository.findById(
                            changeRequest.getId()
                    );

            if (existingRequest.isPresent()) {

                ChangeRequest request =
                        existingRequest.get();

                String newStatus =
                        approval.getDecision().toUpperCase();

                request.setStatus(newStatus);
                request.setUpdatedAt(LocalDateTime.now());

                ChangeRequest savedRequest =
                        changeRequestRepository.save(request);

                // Record CCB decision in status history
                StatusHistory history =
                        new StatusHistory();

                history.setStatus(newStatus);

                history.setComments(
                        approval.getComments()
                );

                history.setChangedAt(
                        LocalDateTime.now()
                );

                history.setChangeRequest(
                        savedRequest
                );

                history.setChangedBy(
                        approval.getApprovedBy()
                );

                statusHistoryRepository.save(history);
            }
        }

        return savedApproval;
    }

    // Get all approvals
    public List<ChangeApproval> getAllApprovals() {

        return changeApprovalRepository.findAll();
    }

    // Get approval by ID
    public Optional<ChangeApproval> getApprovalById(
            Long id) {

        return changeApprovalRepository.findById(id);
    }

    // Get approvals for a change request
    public List<ChangeApproval> getApprovalsByChangeRequest(
            Long changeRequestId) {

        return changeApprovalRepository
                .findByChangeRequestId(changeRequestId);
    }

    // Get approvals made by a user
    public List<ChangeApproval> getApprovalsByUser(
            Long userId) {

        return changeApprovalRepository
                .findByApprovedById(userId);
    }

    // Get approvals by decision
    public List<ChangeApproval> getApprovalsByDecision(
            String decision) {

        return changeApprovalRepository
                .findByDecision(decision);
    }

    // Delete approval
    public void deleteApproval(Long id) {

        changeApprovalRepository.deleteById(id);
    }
}