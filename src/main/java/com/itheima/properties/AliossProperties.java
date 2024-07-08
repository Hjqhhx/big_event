package com.itheima.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
public class AliossProperties {

    private String ENDPOINT;
    private String ACCESS_KEY_ID;
    private String SECRET_ACCESS_KEY;
    private String BUCKET_NAME;
}

