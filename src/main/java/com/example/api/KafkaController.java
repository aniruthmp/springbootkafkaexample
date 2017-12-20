package com.example.api;

import com.example.dsl.KafkaPublish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KafkaController {

    @Value("${spring.cloud.stream.bindings.output.destination}")
    private String topic;

    @Autowired
    private KafkaPublish kafkaPublish;

    @PostMapping(value = "/publish", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> vote(@RequestBody String message) {

        try {
            log.info("Sending message: " + message +
                    " to topic: " + topic);

            kafkaPublish.sendMessage(topic, message);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Successfully posted to Kafka", HttpStatus.OK);
    }

}