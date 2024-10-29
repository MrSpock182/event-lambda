package io.github.mrspock182.kafka_lambda.service;

import io.github.mrspock182.kafka_lambda.messaging.dto.LambdaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerService.class);

    private final UmaNovaClasse umaNovaClasse;

    public LoggerService(UmaNovaClasse umaNovaClasse) {
        this.umaNovaClasse = umaNovaClasse;
    }

    public void log(final LambdaMessage lambdaMessage) {
        LOGGER.info("Mensagem SQS: {}", lambdaMessage);
        umaNovaClasse.log(lambdaMessage);
    }

}
