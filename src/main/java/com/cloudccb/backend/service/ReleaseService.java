package com.cloudccb.backend.service;

import com.cloudccb.backend.entity.Release;
import com.cloudccb.backend.repository.ReleaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReleaseService {

    private final ReleaseRepository releaseRepository;

    public ReleaseService(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    // Create a new release
    public Release createRelease(Release release) {

        if (release.getCreatedAt() == null) {
            release.setCreatedAt(LocalDateTime.now());
        }

        if (release.getStatus() == null ||
                release.getStatus().isBlank()) {
            release.setStatus("DRAFT");
        }

        return releaseRepository.save(release);
    }

    // Get all releases
    public List<Release> getAllReleases() {

        return releaseRepository.findAll();
    }

    // Get release by ID
    public Optional<Release> getReleaseById(Long id) {

        return releaseRepository.findById(id);
    }

    // Get releases by project
    public List<Release> getReleasesByProject(Long projectId) {

        return releaseRepository.findByProjectId(projectId);
    }

    // Get releases by baseline
    public List<Release> getReleasesByBaseline(Long baselineId) {

        return releaseRepository.findByBaselineId(baselineId);
    }

    // Get releases by status
    public List<Release> getReleasesByStatus(String status) {

        return releaseRepository.findByStatus(status);
    }

    // Get releases created by a user
    public List<Release> getReleasesByUser(Long userId) {

        return releaseRepository.findByCreatedById(userId);
    }

    // Mark a release as READY
    public Optional<Release> markReady(Long releaseId) {

        Optional<Release> releaseOptional =
                releaseRepository.findById(releaseId);

        if (releaseOptional.isEmpty()) {
            return Optional.empty();
        }

        Release release = releaseOptional.get();

        release.setStatus("READY");

        return Optional.of(
                releaseRepository.save(release)
        );
    }

    // Publish the release
    public Optional<Release> publishRelease(Long releaseId) {

        Optional<Release> releaseOptional =
                releaseRepository.findById(releaseId);

        if (releaseOptional.isEmpty()) {
            return Optional.empty();
        }

        Release release = releaseOptional.get();

        release.setStatus("RELEASED");
        release.setReleaseDate(LocalDateTime.now());

        return Optional.of(
                releaseRepository.save(release)
        );
    }

    // Cancel a release
    public Optional<Release> cancelRelease(Long releaseId) {

        Optional<Release> releaseOptional =
                releaseRepository.findById(releaseId);

        if (releaseOptional.isEmpty()) {
            return Optional.empty();
        }

        Release release = releaseOptional.get();

        release.setStatus("CANCELLED");

        return Optional.of(
                releaseRepository.save(release)
        );
    }

    // Delete release
    public void deleteRelease(Long id) {

        releaseRepository.deleteById(id);
    }
}