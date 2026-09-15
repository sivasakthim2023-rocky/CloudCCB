package com.cloudccb.backend.repository;

import com.cloudccb.backend.entity.ConfigurationItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigurationItemRepository
        extends JpaRepository<ConfigurationItem, Long> {

    List<ConfigurationItem> findByProjectId(Long projectId);
}