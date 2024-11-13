package com.baas.analytics.main.controller;
import com.baas.analytics.main.entity.Api;
import com.baas.analytics.main.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apis")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @PostMapping
    public ResponseEntity<Api> createApi(@RequestBody Api api) {
        Api savedApi = apiService.createApi(api);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedApi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Api> getApiById(@PathVariable Long id) {
        Optional<Api> api = apiService.getApiById(id);
        return api.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Api> getAllApis() {
        return apiService.getAllApis();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Api> updateApi(@PathVariable Long id, @RequestBody Api api) {
        Api updatedApi = apiService.updateApi(id, api);
        return updatedApi != null ? ResponseEntity.ok(updatedApi) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApi(@PathVariable Long id) {
        apiService.deleteApi(id);
        return ResponseEntity.noContent().build();
    }
}
