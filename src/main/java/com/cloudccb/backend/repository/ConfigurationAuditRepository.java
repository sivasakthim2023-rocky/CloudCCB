package com.cloudccb.backend.repository;

import com.cloudccb.backend.entity.ConfigurationAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigurationAuditRepository
        extends JpaRepository<ConfigurationAudit, Long> {

    List<ConfigurationAudit> findByProjectId(Long projectId);

    List<ConfigurationAudit> findByBaselineId(Long baselineId);

    List<ConfigurationAudit> findByAuditType(String auditType);

    List<ConfigurationAudit> findByResult(String result);

    List<ConfigurationAudit> findByAuditedById(Long userId);
}