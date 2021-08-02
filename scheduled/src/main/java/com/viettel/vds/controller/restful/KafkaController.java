package com.viettel.vds.controller.restful;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vn.com.viettel.vds.arch.config.kafka.PushDataToKafkaUtils;
import vn.com.viettel.vds.arch.factory.response.GeneralResponse;
import vn.com.viettel.vds.arch.factory.response.ResponseFactory;

@RestController
@RequestMapping("/user-management/v1/api")
@Tag(name = "testKafka", description = "testKafka")
@Slf4j
public class KafkaController {
    @Autowired
    RestTemplate restTemplatel;
    @Autowired(required = false)
    PushDataToKafkaUtils pushDataToKafkaUtils;
    @Autowired
    ResponseFactory responseFactory;

    @GetMapping(value = "/kafka")
    public ResponseEntity<GeneralResponse<String>> getKafka(@RequestParam(value = "topic", defaultValue = "springKafkaTest") String topic) {
        pushDataToKafkaUtils.push("springKafkaTest Data", topic);
        return responseFactory.success(topic);
    }

}
