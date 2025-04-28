package com.gizem.freelancemarketplaceapi.message;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FreelancerEvaluationPublisher {

    private final AmqpTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${RABBITMQ_ROUTING_KEY}")
    private String routingKey;

    public FreelancerEvaluationPublisher(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishEvaluation(Long freelancerId) {
        rabbitTemplate.convertAndSend(exchange, routingKey, freelancerId);
        System.out.println("Evaluation message sent for freelancer id: " + freelancerId);
    }
}