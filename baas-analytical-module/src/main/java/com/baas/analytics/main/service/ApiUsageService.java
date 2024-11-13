package com.baas.analytics.main.service;
import com.baas.analytics.main.entity.ApiUsage;
import com.baas.analytics.main.repository.ApiUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApiUsageService {

    @Autowired
    private ApiUsageRepository apiUsageRepository;

    public ApiUsage createApiUsage(ApiUsage apiUsage) {
        apiUsage.setLastUpdated(LocalDateTime.now());
        return apiUsageRepository.save(apiUsage);
    }

    public Optional<ApiUsage> getApiUsageById(Long id) {
        return apiUsageRepository.findById(id);
    }

    public List<ApiUsage> getAllApiUsages() {
        return apiUsageRepository.findAll();
    }

    public ApiUsage updateApiUsage(Long id, ApiUsage apiUsage) {
        if (apiUsageRepository.existsById(id)) {
            apiUsage.setId(id);
            apiUsage.setLastUpdated(LocalDateTime.now());
            return apiUsageRepository.save(apiUsage);
        }
        return null;
    }

    public void deleteApiUsage(Long id) {
        apiUsageRepository.deleteById(id);
    }
}
