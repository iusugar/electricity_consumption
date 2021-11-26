package com.zust.mqtt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mqtt")
public class MqttProperties {
  private String host;
  private String clientId;
  private String[] topic;
  private String username;
  private String password;
  private Integer timeout;
  private Integer keepalive;
}
