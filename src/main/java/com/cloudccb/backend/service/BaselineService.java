package com.cloudccb.backend.service;

import com.cloudccb.backend.entity.Baseline;
import com.cloudccb.backend.repository.BaselineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BaselineService {

    private final BaselineRepository baselineRepository;

    public BaselineService(BaselineRepository baselineRepository) {
        this.baselineRepository = baselineRepository;
    }

    // Create a new baseline
    public Baseline createBaseline(Baseline baseline) {

        if (baseline.getCreatedAt() == null) {
            baseline.setCreatedAt(LocalDateTime.now());
        }

        if (baseline.getStatus() == null ||
                baseline.getStatus().isBlank()) {
            baseline.setStatus("DRAFT");
        }

        return baselineRepository.save(baseline);
    }

    // Get all baselines
    public List<Baseline> getAllBaselines() {

        return baselineRepository.findAll();
    }

    // Get baseline by ID
    public Optional<Baseline> getBaselineById(Long id) {

        return baselineRepository.findById(id);
    }

    // Get baselines belonging to a project
    public List<Baseline> getBaselinesByProject(Long projectId) {

        return baselineRepository.findByProjectId(projectId);
    }

    // Get baselines by status
    public List<Baseline> getBaselinesByStatus(String status) {

        return baselineRepository.findByStatus(status);
    }

    // Get baselines created by a user
    public List<Baseline> getBaselinesByUser(Long userId) {

        return baselineRepository.findByCreatedById(userId);
    }

    // Approve a baseline
    public Optional<Baseline> approveBaseline(
            Long baselineId,
            Long approverId) {

        Optional<Baseline> baselineOptional =
                baselineRepository.findById(baselineId);

        if (baselineOptional.isEmpty()) {
            return Optional.empty();
        }

        Baseline baseline = baselineOptional.get();

        baseline.setStatus("APPROVED");
        baseline.setApprovedAt(LocalDateTime.now());

        if (approverId != null) {
            com.cloudccb.backend.entity.User approver =
                    new com.cloudccb.backend.entity.User();

            approver.setId(approverId);

            baseline.setApprovedBy(approver);
        }

        return Optional.of(
                baselineRepository.save(baseline)
        );
    }

    // Delete baseline
    public void deleteBaseline(Long id) {

        baselineRepository.deleteById(id);
    }
}