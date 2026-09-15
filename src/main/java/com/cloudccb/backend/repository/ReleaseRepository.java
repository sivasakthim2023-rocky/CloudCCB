package com.cloudccb.backend.repository;

import com.cloudccb.backend.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReleaseRepository
        extends JpaRepository<Release, Long> {

    List<Release> findByProjectId(Long projectId);

    List<Release> findByBaselineId(Long baselineId);

    List<Release> findByStatus(String status);

    List<Release> findByCreatedById(Long userId);
}