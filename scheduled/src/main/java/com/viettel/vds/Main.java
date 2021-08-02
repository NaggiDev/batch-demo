package com.viettel.vds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import vn.com.viettel.vds.arch.annotation.EnableArchetype;

@SpringBootApplication(
        exclude = KafkaAutoConfiguration.class
        , scanBasePackages = {"com.viettel.vds"}
)
@EnableCaching// không dùng thì tắt bỏ
@EnableArchetype // Require
//@EnableScheduling
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
