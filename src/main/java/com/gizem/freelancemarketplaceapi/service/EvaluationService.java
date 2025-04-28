package com.gizem.freelancemarketplaceapi.service;

import com.gizem.freelancemarketplaceapi.entity.Freelancer;
import com.gizem.freelancemarketplaceapi.entity.FreelancerType;
import com.gizem.freelancemarketplaceapi.repository.FreelancerRepository;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService {

    private final FreelancerRepository freelancerRepository;

    public EvaluationService(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }

    public void evaluateFreelancer(Long freelancerId) {
        Freelancer freelancer = freelancerRepository.findById(freelancerId).orElse(null);
        if (freelancer == null) {
            System.out.println("Freelancer not found with id: " + freelancerId);
            return;
        }

        int score = 1;
        if (freelancer.getFreelancerType() == FreelancerType.DESIGNER) {
            String tools = freelancer.getDesignTools();
            score = (tools != null) ? tools.split(",").length : 1;
        } else if (freelancer.getFreelancerType() == FreelancerType.SOFTWARE_DEVELOPER) {
            int langs = (freelancer.getSoftwareLanguages() != null) ? freelancer.getSoftwareLanguages().split(",").length : 1;
            int specs = (freelancer.getSpecialties() != null) ? freelancer.getSpecialties().split(",").length : 1;
            score = langs * specs;
        }

        score = Math.min(score, 10);
        freelancer.setEvaluationScore(score);
        freelancerRepository.save(freelancer);

        System.out.println("Freelancer " + freelancerId + " evaluated successfully with score: " + score);
    }
}