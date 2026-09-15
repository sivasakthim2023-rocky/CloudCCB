package com.cloudccb.backend.controller;

import com.cloudccb.backend.entity.Release;
import com.cloudccb.backend.service.ReleaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/releases")
@CrossOrigin(origins = "http://localhost:3000")
public class ReleaseController {

    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    // Create a new release
    @PostMapping
    public ResponseEntity<Release> createRelease(
            @RequestBody Release release) {

        return ResponseEntity.ok(
                releaseService.createRelease(release)
        );
    }

    // Get all releases
    @GetMapping
    public ResponseEntity<List<Release>> getAllReleases() {

        return ResponseEntity.ok(
                releaseService.getAllReleases()
        );
    }

    // Get release by ID
    @GetMapping("/{id}")
    public ResponseEntity<Release> getReleaseById(
            @PathVariable Long id) {

        return releaseService.getReleaseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get releases by project
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Release>> getByProject(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                releaseService.getReleasesByProject(projectId)
        );
    }

    // Get releases by baseline
    @GetMapping("/baseline/{baselineId}")
    public ResponseEntity<List<Release>> getByBaseline(
            @PathVariable Long baselineId) {

        return ResponseEntity.ok(
                releaseService.getReleasesByBaseline(baselineId)
        );
    }

    // Get releases by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Release>> getByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                releaseService.getReleasesByStatus(status)
        );
    }

    // Get releases created by a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Release>> getByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                releaseService.getReleasesByUser(userId)
        );
    }

    // Mark release as READY
    @PutMapping("/{releaseId}/ready")
    public ResponseEntity<Release> markReady(
            @PathVariable Long releaseId) {

        return releaseService
                .markReady(releaseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Publish release
    @PutMapping("/{releaseId}/publish")
    public ResponseEntity<Release> publishRelease(
            @PathVariable Long releaseId) {

        return releaseService
                .publishRelease(releaseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cancel release
    @PutMapping("/{releaseId}/cancel")
    public ResponseEntity<Release> cancelRelease(
            @PathVariable Long releaseId) {

        return releaseService
                .cancelRelease(releaseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete release
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelease(
            @PathVariable Long id) {

        releaseService.deleteRelease(id);

        return ResponseEntity.noContent().build();
    }
}