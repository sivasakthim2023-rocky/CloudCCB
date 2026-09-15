package com.cloudccb.backend.service;

import com.cloudccb.backend.entity.ConfigurationItem;
import com.cloudccb.backend.repository.ConfigurationItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationItemService {

    private final ConfigurationItemRepository configurationItemRepository;

    public ConfigurationItemService(ConfigurationItemRepository configurationItemRepository) {
        this.configurationItemRepository = configurationItemRepository;
    }

    public ConfigurationItem createConfigurationItem(ConfigurationItem item) {
        return configurationItemRepository.save(item);
    }

    public List<ConfigurationItem> getAllConfigurationItems() {
        return configurationItemRepository.findAll();
    }

    public Optional<ConfigurationItem> getConfigurationItemById(Long id) {
        return configurationItemRepository.findById(id);
    }

    public List<ConfigurationItem> getConfigurationItemsByProject(Long projectId) {
        return configurationItemRepository.findByProjectId(projectId);
    }

    public void deleteConfigurationItem(Long id) {
        configurationItemRepository.deleteById(id);
    }
}