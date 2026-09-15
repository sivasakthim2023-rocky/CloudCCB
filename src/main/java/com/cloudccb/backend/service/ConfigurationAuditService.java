package com.cloudccb.backend.service;

import com.cloudccb.backend.entity.ConfigurationAudit;
import com.cloudccb.backend.repository.ConfigurationAuditRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationAuditService {

    private final ConfigurationAuditRepository configurationAuditRepository;

    public ConfigurationAuditService(
            ConfigurationAuditRepository configurationAuditRepository) {

        this.configurationAuditRepository = configurationAuditRepository;
    }

    // Create a configuration audit
    public ConfigurationAudit createAudit(
            ConfigurationAudit audit) {

        if (audit.getAuditDate() == null) {
            audit.setAuditDate(LocalDateTime.now());
        }

        return configurationAuditRepository.save(audit);
    }

    // Get all audits
    public List<ConfigurationAudit> getAllAudits() {

        return configurationAuditRepository.findAll();
    }

    // Get audit by ID
    public Optional<ConfigurationAudit> getAuditById(Long id) {

        return configurationAuditRepository.findById(id);
    }

    // Get audits for a project
    public List<ConfigurationAudit> getAuditsByProject(
            Long projectId) {

        return configurationAuditRepository
                .findByProjectId(projectId);
    }

    // Get audits for a baseline
    public List<ConfigurationAudit> getAuditsByBaseline(
            Long baselineId) {

        return configurationAuditRepository
                .findByBaselineId(baselineId);
    }

    // Get audits by type: FCA / PCA
    public List<ConfigurationAudit> getAuditsByType(
            String auditType) {

        return configurationAuditRepository
                .findByAuditType(auditType);
    }

    // Get audits by result: PASSED / FAILED
    public List<ConfigurationAudit> getAuditsByResult(
            String result) {

        return configurationAuditRepository
                .findByResult(result);
    }

    // Get audits performed by a user
    public List<ConfigurationAudit> getAuditsByAuditor(
            Long userId) {

        return configurationAuditRepository
                .findByAuditedById(userId);
    }

    // Delete audit
    public void deleteAudit(Long id) {

        configurationAuditRepository.deleteById(id);
    }
}