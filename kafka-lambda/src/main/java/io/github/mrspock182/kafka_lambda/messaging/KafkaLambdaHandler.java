package io.github.mrspock182.kafka_lambda.messaging;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mrspock182.kafka_lambda.messaging.dto.LambdaMessage;
import io.github.mrspock182.kafka_lambda.service.LoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class KafkaLambdaHandler implements Function<SQSEvent, String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaLambdaHandler.class);

    private final LoggerService loggerService;

    public KafkaLambdaHandler(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @Override
    public String apply(final SQSEvent event) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();

            for (SQSEvent.SQSMessage message : event.getRecords()) {
                Map<String, SQSEvent.MessageAttribute> header = message.getMessageAttributes();

                if (header.containsKey("EMAIL")) {
                    final LambdaMessage lambdaMessage = objectMapper.readValue(message.getBody(), LambdaMessage.class);
                    if (lambdaMessage.message().equals("Ronaldo")) {
                        throw new IllegalArgumentException("Aconteceu um erro na leitura");
                    }
                    loggerService.log(lambdaMessage);
                } else {
                    LOGGER.info("A mensagem chegou, mas não é um email");
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Mensagem Error: {}", ex.getMessage());
            throw new RuntimeException("Message processing failed", ex);
        }

        return null;
    }
}