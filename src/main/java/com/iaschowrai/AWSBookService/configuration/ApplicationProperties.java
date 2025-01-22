package com.iaschowrai.AWSBookService.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "book-service")
public record ApplicationProperties(String queue, String bucket) {
}
