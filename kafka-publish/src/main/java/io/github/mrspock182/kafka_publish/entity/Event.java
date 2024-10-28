package io.github.mrspock182.kafka_publish.entity;

public record Event(
        String code,
        String message,
        String email,
        String phone
) {
}