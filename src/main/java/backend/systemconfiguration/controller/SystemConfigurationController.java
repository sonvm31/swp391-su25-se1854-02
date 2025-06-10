package backend.systemconfiguration.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.systemconfiguration.dto.CreateSystemConfigurationRequest;
import backend.systemconfiguration.dto.UpdateSystemConfigurationRequest;
import backend.systemconfiguration.model.SystemConfiguration;
import backend.systemconfiguration.service.SystemConfigurationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/system-configuration")
@RequiredArgsConstructor
public class SystemConfigurationController {

    private final SystemConfigurationService systemConfigurationService;

    @PostMapping
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateSystemConfigurationRequest request) {
        return ResponseEntity.ok(Map.of("message", systemConfigurationService.create(request)));
    }

    @GetMapping
    public ResponseEntity<List<SystemConfiguration>> list() {
        return ResponseEntity.ok(systemConfigurationService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemConfiguration> get(@PathVariable long id) {
        return ResponseEntity.ok(systemConfigurationService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable long id, @RequestBody UpdateSystemConfigurationRequest request) {
        return ResponseEntity.ok(Map.of("message", systemConfigurationService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable long id) {
        return ResponseEntity.ok(Map.of("message", systemConfigurationService.delete(id)));
    }
}
