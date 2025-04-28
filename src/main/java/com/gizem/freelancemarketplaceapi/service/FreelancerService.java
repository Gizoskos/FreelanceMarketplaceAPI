package com.gizem.freelancemarketplaceapi.service;

import com.gizem.freelancemarketplaceapi.dto.FreelancerDTO;
import com.gizem.freelancemarketplaceapi.entity.Freelancer;
import com.gizem.freelancemarketplaceapi.entity.FreelancerType;

import java.util.List;

public interface FreelancerService {
    Freelancer createFreelancer(FreelancerDTO dto);
    Freelancer getFreelancer(Long id);
    List<Freelancer> getAllFreelancers();
    List<Freelancer> searchBy(String name, String city, FreelancerType type, String specialty, String tool);
}