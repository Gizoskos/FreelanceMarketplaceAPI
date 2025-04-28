package com.gizem.freelancemarketplaceapi.repository;


import com.gizem.freelancemarketplaceapi.entity.Freelancer;
import com.gizem.freelancemarketplaceapi.entity.FreelancerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
    List<Freelancer> findByNameContaining(String name);
    List<Freelancer> findByCityContaining(String city);
    List<Freelancer> findByFreelancerType(FreelancerType freelancerType);
    List<Freelancer> findBySpecialtiesContaining(String specialty);
    List<Freelancer> findByDesignToolsContaining(String tool);
}