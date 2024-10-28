package io.github.mrspock182.kafka_publish.messaging.dto;

public record LambdaMessage(
        String code,
        String message
) {
}
