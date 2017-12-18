package com.example;

import com.example.dsl.KafkaPublish;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
public class SpringBootKafkaController {

    @Value("${spring.cloud.stream.bindings.output.destination}")
    private String topic;

    @Autowired
    private KafkaPublish kafkaPublish;

    @PutMapping("/publish")
    public ResponseEntity<String> vote() {

        try {
            TimeInfo timeInfo = new TimeInfo();
            timeInfo.setLabel(RandomStringUtils.random(10, true, false));
            timeInfo.setTime(new Date().getTime() + "");
            String message = JsonUtil.toJson(timeInfo);
            log.info("Sending message: " + message +
                    " to topic: " + topic);

            kafkaPublish.sendMessage(topic, message);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}