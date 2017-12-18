package com.example.dsl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by a.c.parthasarathy on 10/25/16.
 */
@Slf4j
@Component
public class DefaultListener {

    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(id = "${spring.cloud.stream.bindings.input.group}",
            topics = "${spring.cloud.stream.bindings.input.destination}")
    public void onMessage(String message) {
        log.info("received message='{}'", message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}