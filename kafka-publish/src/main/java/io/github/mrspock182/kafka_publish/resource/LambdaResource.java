package io.github.mrspock182.kafka_publish.resource;

import io.github.mrspock182.kafka_publish.entity.Event;
import io.github.mrspock182.kafka_publish.resource.dto.LambdaRequest;
import io.github.mrspock182.kafka_publish.service.PublishEventService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/publish/v1")
public class LambdaResource {

    private final PublishEventService publishEventService;

    public LambdaResource(PublishEventService publishEventService) {
        this.publishEventService = publishEventService;
    }

    @ResponseStatus(OK)
    @PostMapping("/lambda")
    public void pagar(@RequestBody final LambdaRequest request) {
        publishEventService.publish(new Event(request.code(), request.message(), request.email(), request.phone()));
    }

    @ResponseStatus(OK)
    @GetMapping("/lambda")
    public String pagar() {
        return "Hello World";
    }

}
