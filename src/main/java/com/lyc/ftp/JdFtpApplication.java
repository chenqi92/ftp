package com.lyc.ftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 功能:
 *
 * @author chenQi
 * @version 1.0
 * @date 2020/11/13 16:22
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class JdFtpApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdFtpApplication.class);
    }
}
