package com.gizem.freelancemarketplaceapi.message;
import com.gizem.freelancemarketplaceapi.service.EvaluationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class FreelancerEvaluationListener {

    private final EvaluationService evaluationService;

    @Value("${RABBITMQ_QUEUE}")
    private String queueName;

    public FreelancerEvaluationListener(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @RabbitListener(queues = "${RABBITMQ_QUEUE}")
    public void handleEvaluation(Long freelancerId) {
        System.out.println("🔔 Received evaluation message for freelancer id: " + freelancerId);
        evaluationService.evaluateFreelancer(freelancerId);
    }
}