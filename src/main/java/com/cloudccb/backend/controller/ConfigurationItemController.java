package com.cloudccb.backend.controller;

import com.cloudccb.backend.entity.ConfigurationItem;
import com.cloudccb.backend.service.ConfigurationItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configuration-items")
@CrossOrigin(origins = "http://localhost:3000")
public class ConfigurationItemController {

    private final ConfigurationItemService configurationItemService;

    public ConfigurationItemController(
            ConfigurationItemService configurationItemService) {
        this.configurationItemService = configurationItemService;
    }

    @PostMapping
    public ResponseEntity<ConfigurationItem> createConfigurationItem(
            @RequestBody ConfigurationItem item) {

        return ResponseEntity.ok(
                configurationItemService.createConfigurationItem(item)
        );
    }

    @GetMapping
    public ResponseEntity<List<ConfigurationItem>> getAllConfigurationItems() {
        return ResponseEntity.ok(
                configurationItemService.getAllConfigurationItems()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfigurationItem> getConfigurationItemById(
            @PathVariable Long id) {

        return configurationItemService.getConfigurationItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ConfigurationItem>> getByProject(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                configurationItemService
                        .getConfigurationItemsByProject(projectId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConfigurationItem(
            @PathVariable Long id) {

        configurationItemService.deleteConfigurationItem(id);
        return ResponseEntity.noContent().build();
    }
}