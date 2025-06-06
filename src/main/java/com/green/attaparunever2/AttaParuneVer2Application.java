package com.green.attaparunever2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //auditing 기능 활성화
@ConfigurationPropertiesScan
@SpringBootApplication
public class AttaParuneVer2Application {
    public static void main(String[] args) {
        SpringApplication.run(AttaParuneVer2Application.class, args);
    }
}
