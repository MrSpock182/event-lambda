package io.github.mrspock182.kafka_publish.messaging;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mrspock182.kafka_publish.messaging.dto.LambdaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LambdaPublishWithSQS {
    private static final Logger logger = LoggerFactory.getLogger(LambdaPublishWithSQS.class);

    private final String sqsUrl;
    private final AmazonSQS amazonSQS;
    private final ObjectMapper objectMapper;

    public LambdaPublishWithSQS(
            @Value("${spring.sql.url}") final String sqsUrl,
            final AmazonSQS amazonSQS,
            final ObjectMapper objectMapper) {
        this.sqsUrl = sqsUrl;
        this.amazonSQS = amazonSQS;
        this.objectMapper = objectMapper;
    }

    public void publish(final List<String> types, final LambdaMessage lambdaMessage) {
        try {
            final SendMessageResult result = amazonSQS.sendMessage(getRequest(types, lambdaMessage));
            logger.info("Mensagem enviada: {}", result.getMessageId());
        } catch (Exception ex) {
            logger.error("Erro ao enviar mensagem: {}", ex.getMessage());
        }
    }

    private SendMessageRequest getRequest(
            final List<String> types,
            final LambdaMessage lambdaMessage) throws JsonProcessingException {
        Map<String, MessageAttributeValue> header = new HashMap<>();
        types.forEach(v -> {
            MessageAttributeValue messageAttributeValue = new MessageAttributeValue();
            messageAttributeValue.setDataType("String");
            messageAttributeValue.setStringValue(v);
            header.put(v, messageAttributeValue);
        });
        SendMessageRequest request = new SendMessageRequest(sqsUrl, objectMapper.writeValueAsString(lambdaMessage));
        request.setMessageAttributes(header);
        return request;
    }

}
