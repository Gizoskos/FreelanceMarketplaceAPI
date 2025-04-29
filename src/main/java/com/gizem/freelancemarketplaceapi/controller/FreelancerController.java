package com.gizem.freelancemarketplaceapi.controller;

import com.gizem.freelancemarketplaceapi.exception.ResourceNotFoundException;
import com.gizem.freelancemarketplaceapi.dto.FreelancerDTO;
import com.gizem.freelancemarketplaceapi.entity.Freelancer;
import com.gizem.freelancemarketplaceapi.entity.FreelancerType;
import com.gizem.freelancemarketplaceapi.service.FreelancerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v{version}/freelancers")
public class FreelancerController {

    private final FreelancerService freelancerService;

    public FreelancerController(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }

    @PostMapping
    public ResponseEntity<Freelancer> createFreelancer(@PathVariable String version, @RequestBody @Valid FreelancerDTO dto) {
        Freelancer created = freelancerService.createFreelancer(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Freelancer>> getAll(@PathVariable String version) {
        return ResponseEntity.ok(freelancerService.getAllFreelancers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Freelancer> getById(@PathVariable String version, @PathVariable Long id) {
        Freelancer freelancer = freelancerService.getFreelancer(id);
        if (freelancer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(freelancer);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Freelancer>> search(
            @PathVariable String version,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) FreelancerType type,
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) String tool
    ) {
        return ResponseEntity.ok(freelancerService.searchBy(name, city, type, specialty, tool));
    }
}