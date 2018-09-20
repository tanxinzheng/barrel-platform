package com.github.tanxinzheng.fss;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by tanxinzheng on 2018/9/20.
 */
@ConfigurationProperties(value = "fss")
public class FssConfigProperties {

    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
    private String bucketName;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
