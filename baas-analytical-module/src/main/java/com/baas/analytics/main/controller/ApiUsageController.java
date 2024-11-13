package com.baas.analytics.main.controller;

import com.baas.analytics.main.entity.ApiUsage;
import com.baas.analytics.main.service.ApiUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-usages")
public class ApiUsageController {

    @Autowired
    private ApiUsageService apiUsageService;

    @PostMapping
    public ResponseEntity<ApiUsage> createApiUsage(@RequestBody ApiUsage apiUsage) {
        ApiUsage savedApiUsage = apiUsageService.createApiUsage(apiUsage);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedApiUsage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiUsage> getApiUsageById(@PathVariable Long id) {
        Optional<ApiUsage> apiUsage = apiUsageService.getApiUsageById(id);
        return apiUsage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ApiUsage> getAllApiUsages() {
        return apiUsageService.getAllApiUsages();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiUsage> updateApiUsage(@PathVariable Long id, @RequestBody ApiUsage apiUsage) {
        ApiUsage updatedApiUsage = apiUsageService.updateApiUsage(id, apiUsage);
        return updatedApiUsage != null ? ResponseEntity.ok(updatedApiUsage) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApiUsage(@PathVariable Long id) {
        apiUsageService.deleteApiUsage(id);
        return ResponseEntity.noContent().build();
    }
}
