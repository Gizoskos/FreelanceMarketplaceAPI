package com.gizem.freelancemarketplaceapi.entity;

import jakarta.persistence.*;

@Entity
public class Freelancer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String city;
    @Enumerated(EnumType.STRING)
    private FreelancerType freelancerType;

    // Designer
    private String portfolioUrl;
    private String designTools;

    // Developer
    private String softwareLanguages;
    private String specialties;

    private Integer evaluationScore;


    public Freelancer() {}

    public Freelancer(Long id, String name, String email, String phone, String city, FreelancerType freelancerType,
                      String portfolioUrl, String designTools,
                      String softwareLanguages, String specialties, Integer evaluationScore) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.freelancerType = freelancerType;
        this.portfolioUrl = portfolioUrl;
        this.designTools = designTools;
        this.softwareLanguages = softwareLanguages;
        this.specialties = specialties;
        this.evaluationScore = evaluationScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public FreelancerType getFreelancerType() {
        return freelancerType;
    }

    public void setFreelancerType(FreelancerType freelancerType) {
        this.freelancerType = freelancerType;
    }

    public String getPortfolioUrl() {
        return portfolioUrl;
    }

    public void setPortfolioUrl(String portfolioUrl) {
        this.portfolioUrl = portfolioUrl;
    }

    public String getDesignTools() {
        return designTools;
    }

    public void setDesignTools(String designTools) {
        this.designTools = designTools;
    }

    public String getSoftwareLanguages() {
        return softwareLanguages;
    }

    public void setSoftwareLanguages(String softwareLanguages) {
        this.softwareLanguages = softwareLanguages;
    }

    public String getSpecialties() {
        return specialties;
    }

    public void setSpecialties(String specialties) {
        this.specialties = specialties;
    }

    public Integer getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(Integer evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

}
