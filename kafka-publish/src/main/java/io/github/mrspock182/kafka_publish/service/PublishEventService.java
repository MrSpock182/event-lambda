package io.github.mrspock182.kafka_publish.service;

import io.github.mrspock182.kafka_publish.entity.Event;
import io.github.mrspock182.kafka_publish.messaging.LambdaPublishWithSQS;
import io.github.mrspock182.kafka_publish.messaging.dto.LambdaMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PublishEventService {
    private final LambdaPublishWithSQS lambdaPublish;

    public PublishEventService(LambdaPublishWithSQS lambdaPublish) {
        this.lambdaPublish = lambdaPublish;
    }

    public void publish(final Event event) {
        List<String> list = new ArrayList<>();

        if (!Objects.equals(event.phone(), "")) {
            list.add("PHONE");
        }

        if (!Objects.equals(event.email(), "")) {
            list.add("EMAIL");
        }

        lambdaPublish.publish(list, new LambdaMessage(event.code(), event.message()));
    }

}
