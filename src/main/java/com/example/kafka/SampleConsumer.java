package com.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableBinding(Sink.class)
public class SampleConsumer {
    @StreamListener(target = Sink.INPUT)
    public void logMessages(String msg) {
        log.info("Received message is: " + msg);
    }
}
