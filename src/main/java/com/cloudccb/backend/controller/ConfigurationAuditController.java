package com.cloudccb.backend.controller;

import com.cloudccb.backend.entity.ConfigurationAudit;
import com.cloudccb.backend.service.ConfigurationAuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configuration-audits")
@CrossOrigin(origins = "http://localhost:3000")
public class ConfigurationAuditController {

    private final ConfigurationAuditService configurationAuditService;

    public ConfigurationAuditController(
            ConfigurationAuditService configurationAuditService) {

        this.configurationAuditService = configurationAuditService;
    }

    // Create a configuration audit
    @PostMapping
    public ResponseEntity<ConfigurationAudit> createAudit(
            @RequestBody ConfigurationAudit audit) {

        return ResponseEntity.ok(
                configurationAuditService.createAudit(audit)
        );
    }

    // Get all audits
    @GetMapping
    public ResponseEntity<List<ConfigurationAudit>> getAllAudits() {

        return ResponseEntity.ok(
                configurationAuditService.getAllAudits()
        );
    }

    // Get audit by ID
    @GetMapping("/{id}")
    public ResponseEntity<ConfigurationAudit> getAuditById(
            @PathVariable Long id) {

        return configurationAuditService.getAuditById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get audits by project
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ConfigurationAudit>> getByProject(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                configurationAuditService
                        .getAuditsByProject(projectId)
        );
    }

    // Get audits by baseline
    @GetMapping("/baseline/{baselineId}")
    public ResponseEntity<List<ConfigurationAudit>> getByBaseline(
            @PathVariable Long baselineId) {

        return ResponseEntity.ok(
                configurationAuditService
                        .getAuditsByBaseline(baselineId)
        );
    }

    // Get audits by type: FCA / PCA
    @GetMapping("/type/{auditType}")
    public ResponseEntity<List<ConfigurationAudit>> getByType(
            @PathVariable String auditType) {

        return ResponseEntity.ok(
                configurationAuditService
                        .getAuditsByType(auditType)
        );
    }

    // Get audits by result: PASSED / FAILED
    @GetMapping("/result/{result}")
    public ResponseEntity<List<ConfigurationAudit>> getByResult(
            @PathVariable String result) {

        return ResponseEntity.ok(
                configurationAuditService
                        .getAuditsByResult(result)
        );
    }

    // Get audits performed by an auditor
    @GetMapping("/auditor/{userId}")
    public ResponseEntity<List<ConfigurationAudit>> getByAuditor(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                configurationAuditService
                        .getAuditsByAuditor(userId)
        );
    }

    // Delete audit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAudit(
            @PathVariable Long id) {

        configurationAuditService.deleteAudit(id);

        return ResponseEntity.noContent().build();
    }
}