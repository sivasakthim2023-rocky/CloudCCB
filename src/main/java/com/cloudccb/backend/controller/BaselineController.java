package com.cloudccb.backend.controller;

import com.cloudccb.backend.entity.Baseline;
import com.cloudccb.backend.service.BaselineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baselines")
@CrossOrigin(origins = "http://localhost:3000")
public class BaselineController {

    private final BaselineService baselineService;

    public BaselineController(BaselineService baselineService) {
        this.baselineService = baselineService;
    }

    // Create a new baseline
    @PostMapping
    public ResponseEntity<Baseline> createBaseline(
            @RequestBody Baseline baseline) {

        return ResponseEntity.ok(
                baselineService.createBaseline(baseline)
        );
    }

    // Get all baselines
    @GetMapping
    public ResponseEntity<List<Baseline>> getAllBaselines() {

        return ResponseEntity.ok(
                baselineService.getAllBaselines()
        );
    }

    // Get baseline by ID
    @GetMapping("/{id}")
    public ResponseEntity<Baseline> getBaselineById(
            @PathVariable Long id) {

        return baselineService.getBaselineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get baselines by project
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Baseline>> getByProject(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                baselineService.getBaselinesByProject(projectId)
        );
    }

    // Get baselines by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Baseline>> getByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                baselineService.getBaselinesByStatus(status)
        );
    }

    // Get baselines created by a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Baseline>> getByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                baselineService.getBaselinesByUser(userId)
        );
    }

    // Approve a baseline
    @PutMapping("/{baselineId}/approve/{approverId}")
    public ResponseEntity<Baseline> approveBaseline(
            @PathVariable Long baselineId,
            @PathVariable Long approverId) {

        return baselineService
                .approveBaseline(baselineId, approverId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete baseline
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBaseline(
            @PathVariable Long id) {

        baselineService.deleteBaseline(id);

        return ResponseEntity.noContent().build();
    }
}