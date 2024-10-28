package io.github.mrspock182.kafka_publish.resource.dto;

public record LambdaRequest(
        String code,
        String message,
        String email,
        String phone
) {
}
