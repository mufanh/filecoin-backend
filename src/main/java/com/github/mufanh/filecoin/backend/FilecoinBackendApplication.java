package com.github.mufanh.filecoin.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xinquan.huangxq
 */
@SpringBootApplication
@EnableScheduling
public class FilecoinBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilecoinBackendApplication.class, args);
    }
}
