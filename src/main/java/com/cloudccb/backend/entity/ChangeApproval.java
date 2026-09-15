package com.cloudccb.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "change_approvals")
public class ChangeApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String decision;

    @Column(length = 1000)
    private String comments;

    private LocalDateTime decidedAt;

    @ManyToOne
    @JoinColumn(name = "change_request_id")
    private ChangeRequest changeRequest;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    public ChangeApproval() {
    }

    public ChangeApproval(String decision, String comments,
                          LocalDateTime decidedAt,
                          ChangeRequest changeRequest,
                          User approvedBy) {
        this.decision = decision;
        this.comments = comments;
        this.decidedAt = decidedAt;
        this.changeRequest = changeRequest;
        this.approvedBy = approvedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getDecidedAt() {
        return decidedAt;
    }

    public void setDecidedAt(LocalDateTime decidedAt) {
        this.decidedAt = decidedAt;
    }

    public ChangeRequest getChangeRequest() {
        return changeRequest;
    }

    public void setChangeRequest(ChangeRequest changeRequest) {
        this.changeRequest = changeRequest;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }
}