package br.com.rgrassi.events.property;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
@ConfigurationProperties(prefix = "jwt.config")
@Getter
@Setter
@ToString
public class JWTConfiguration {
  private String loginUrl = "/sessions";
  private String singUpUrl = "/signup/";
  private String secretKey = "82B1EB4A3A2231CBB8D822EF2BBBA";
  @NestedConfigurationProperty
  private Header header = new Header();
  private long expiration = 86400000l;

  @Getter
  @Setter
  public static class Header {
    private String name = "Authorization";
    private String prefix = "Bearer ";
  }
}
