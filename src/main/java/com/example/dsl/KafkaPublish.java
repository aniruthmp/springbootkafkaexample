package com.example.dsl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


/**
 * Created by a.c.parthasarathy on 10/26/16.
 */
@Slf4j
@Service
public class KafkaPublish {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {

        kafkaTemplate.send(topic, message);
        log.info("sent message='{}' ", message);
    }
}
