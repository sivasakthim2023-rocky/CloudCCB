package com.cloudccb.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "configuration_audits")
public class ConfigurationAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String auditType;

    private String result;

    @Column(length = 2000)
    private String findings;

    private LocalDateTime auditDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "baseline_id")
    private Baseline baseline;

    @ManyToOne
    @JoinColumn(name = "audited_by")
    private User auditedBy;

    public ConfigurationAudit() {
    }

    public ConfigurationAudit(
            String auditType,
            String result,
            String findings,
            LocalDateTime auditDate,
            Project project,
            Baseline baseline,
            User auditedBy) {

        this.auditType = auditType;
        this.result = result;
        this.findings = findings;
        this.auditDate = auditDate;
        this.project = project;
        this.baseline = baseline;
        this.auditedBy = auditedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public LocalDateTime getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(LocalDateTime auditDate) {
        this.auditDate = auditDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Baseline getBaseline() {
        return baseline;
    }

    public void setBaseline(Baseline baseline) {
        this.baseline = baseline;
    }

    public User getAuditedBy() {
        return auditedBy;
    }

    public void setAuditedBy(User auditedBy) {
        this.auditedBy = auditedBy;
    }
}