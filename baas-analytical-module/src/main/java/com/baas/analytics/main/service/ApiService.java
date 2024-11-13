package com.baas.analytics.main.service;

import com.baas.analytics.main.entity.Api;
import com.baas.analytics.main.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApiService {

    @Autowired
    private ApiRepository apiRepository;

    public Api createApi(Api api) {
        api.setCreatedAt(LocalDateTime.now());
        return apiRepository.save(api);
    }

    public Optional<Api> getApiById(Long id) {
        return apiRepository.findById(id);
    }

    public List<Api> getAllApis() {
        return apiRepository.findAll();
    }

    public Api updateApi(Long id, Api api) {
        if (apiRepository.existsById(id)) {
            api.setId(id);
            return apiRepository.save(api);
        }
        return null;
    }

    public void deleteApi(Long id) {
        apiRepository.deleteById(id);
    }
}
