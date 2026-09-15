package com.cloudccb.backend.repository;

import com.cloudccb.backend.entity.Baseline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaselineRepository
        extends JpaRepository<Baseline, Long> {

    List<Baseline> findByProjectId(Long projectId);

    List<Baseline> findByStatus(String status);

    List<Baseline> findByCreatedById(Long userId);
}