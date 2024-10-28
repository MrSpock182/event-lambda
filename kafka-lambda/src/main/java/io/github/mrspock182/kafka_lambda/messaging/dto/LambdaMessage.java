package io.github.mrspock182.kafka_lambda.messaging.dto;

public record LambdaMessage(
        String code,
        String message
) {
}