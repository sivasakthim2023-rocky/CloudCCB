package com.cloudccb.backend.controller;

import com.cloudccb.backend.entity.ChangeApproval;
import com.cloudccb.backend.service.ChangeApprovalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/change-approvals")
@CrossOrigin(origins = "http://localhost:3000")
public class ChangeApprovalController {

    private final ChangeApprovalService changeApprovalService;

    public ChangeApprovalController(ChangeApprovalService changeApprovalService) {
        this.changeApprovalService = changeApprovalService;
    }

    @PostMapping
    public ResponseEntity<ChangeApproval> createApproval(
            @RequestBody ChangeApproval approval) {

        return ResponseEntity.ok(
                changeApprovalService.createApproval(approval)
        );
    }

    @GetMapping
    public ResponseEntity<List<ChangeApproval>> getAllApprovals() {

        return ResponseEntity.ok(
                changeApprovalService.getAllApprovals()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChangeApproval> getApprovalById(
            @PathVariable Long id) {

        return changeApprovalService.getApprovalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/change-request/{changeRequestId}")
    public ResponseEntity<List<ChangeApproval>> getByChangeRequest(
            @PathVariable Long changeRequestId) {

        return ResponseEntity.ok(
                changeApprovalService
                        .getApprovalsByChangeRequest(changeRequestId)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChangeApproval>> getByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                changeApprovalService
                        .getApprovalsByUser(userId)
        );
    }

    @GetMapping("/decision/{decision}")
    public ResponseEntity<List<ChangeApproval>> getByDecision(
            @PathVariable String decision) {

        return ResponseEntity.ok(
                changeApprovalService
                        .getApprovalsByDecision(decision)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApproval(
            @PathVariable Long id) {

        changeApprovalService.deleteApproval(id);
        return ResponseEntity.noContent().build();
    }
}