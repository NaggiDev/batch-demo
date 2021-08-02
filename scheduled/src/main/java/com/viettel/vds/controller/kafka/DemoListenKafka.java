package com.viettel.vds.controller.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
@Slf4j
public class DemoListenKafka {
    @KafkaListener(topics = "springKafkaTest", groupId = "id")
    public void listenKafkaDemo(@Payload String message) {
        log.info("Nhận đc data từ topic: {} với thông tin là : {}", "springKafkaTest", message);
    }
}
