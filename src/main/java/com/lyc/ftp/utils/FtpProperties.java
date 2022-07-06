package com.lyc.ftp.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能:
 *
 * @author chenQi
 * @version 1.0
 * @date 2020/11/16 15:24
 */
@Data
@ConfigurationProperties(prefix = "ftp")
@Component
public class FtpProperties {

    private String url;

    private Integer port;

    private String username;

    private String password;

    private String path;
}
