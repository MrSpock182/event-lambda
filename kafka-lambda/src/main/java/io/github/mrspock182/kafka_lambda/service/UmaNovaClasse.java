package io.github.mrspock182.kafka_lambda.service;

import io.github.mrspock182.kafka_lambda.messaging.dto.LambdaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UmaNovaClasse {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmaNovaClasse.class);

    public void log(final LambdaMessage lambdaMessage) {
        LOGGER.info("Isso é uma nova mensagem: {}", lambdaMessage);
    }
}
