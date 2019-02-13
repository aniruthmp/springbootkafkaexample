package com.example.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableBinding(Source.class)
@Slf4j
public class KafkaController {

    @Autowired
    Source mySource;

    @GetMapping(path = "/simple")
    public String PublishMessage() {
        String message = "" + System.currentTimeMillis();
        log.info("Posting message: " + message);
        mySource.output().send(MessageBuilder.withPayload(message).build());
        return "success";
    }

}