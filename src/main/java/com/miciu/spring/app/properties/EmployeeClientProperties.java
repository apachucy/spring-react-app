package com.miciu.spring.app.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * configuration:
 * oauth2.client.employee.clientId
 * oauth2.client.employee.clientSecret
 * oauth2.client.employee.accessTokenUri
 * oauth2.client.employee.scopes
 */
@Data
@ConfigurationProperties(prefix = "oauth2.client.employee")
public class EmployeeClientProperties {
  private String clientId;
  private String clientSecret;
  private String accessTokenUri;
  private List<String> scopes;
}
