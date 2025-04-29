package com.gizem.freelancemarketplaceapi.service;

import com.gizem.freelancemarketplaceapi.dto.FreelancerDTO;
import com.gizem.freelancemarketplaceapi.entity.Freelancer;
import com.gizem.freelancemarketplaceapi.entity.FreelancerType;
import com.gizem.freelancemarketplaceapi.exception.ResourceNotFoundException;
import com.gizem.freelancemarketplaceapi.repository.FreelancerRepository;
import com.gizem.freelancemarketplaceapi.message.FreelancerEvaluationPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreelancerServiceImp implements FreelancerService {

    private final FreelancerRepository freelancerRepository;
    private final FreelancerEvaluationPublisher evaluationPublisher;

    public FreelancerServiceImp(FreelancerRepository freelancerRepository, FreelancerEvaluationPublisher evaluationPublisher) {
        this.freelancerRepository = freelancerRepository;
        this.evaluationPublisher = evaluationPublisher;
    }

    @Override
    public Freelancer createFreelancer(FreelancerDTO dto) {
        Freelancer freelancer = new Freelancer();
        freelancer.setName(dto.getName());
        freelancer.setEmail(dto.getEmail());
        freelancer.setPhone(dto.getPhone());
        freelancer.setCity(dto.getCity());
        freelancer.setFreelancerType(dto.getFreelancerType());

        if (dto.getFreelancerType() == FreelancerType.DESIGNER) {
            freelancer.setPortfolioUrl(dto.getPortfolioUrl());
            freelancer.setDesignTools(dto.getDesignTools());
        } else if (dto.getFreelancerType() == FreelancerType.SOFTWARE_DEVELOPER) {
            freelancer.setSoftwareLanguages(dto.getSoftwareLanguages());
            freelancer.setSpecialties(dto.getSpecialties());
        }


        Freelancer saved = freelancerRepository.save(freelancer);
        evaluationPublisher.publishEvaluation(saved.getId()); // async mesaj gönder
        return saved;
    }

    @Override
    public List<Freelancer> getAllFreelancers() {
        return freelancerRepository.findAll();
    }

    @Override
    public Freelancer getFreelancer(Long id) {
        return freelancerRepository.findById(id).orElse(null); }

    @Override
    public List<Freelancer> searchBy(String name, String city, FreelancerType type, String specialty, String tool) {
        if (name != null && !name.isBlank()) {
            return freelancerRepository.findByNameContaining(name);
        }
        if (city != null && !city.isBlank()) {
            return freelancerRepository.findByCityContaining(city);
        }
        if (type != null) {
            return freelancerRepository.findByFreelancerType(type);
        }
        if (specialty != null && !specialty.isBlank()) {
            return freelancerRepository.findBySpecialtiesContaining(specialty);
        }
        if (tool != null && !tool.isBlank()) {
            return freelancerRepository.findByDesignToolsContaining(tool);
        }
        return List.of(); //boş dön
    }
}