package br.com.rgrassi.events.property;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt.config")
@Getter
@Setter
@ToString
public class JWTConfiguration {
  private String loginUrl = "/login/**";
  private String SECRET = "82B1EB4A3A2231CBB8D822EF2BBBA";
  private Header header = new Header();
  private long expiration = 86400000l;
  private String privateKey = "2U4SWK6BRQL1SxHiRhlUbkRhjf1j2HRZ";

  @Getter
  @Setter
  public static class Header {
    private String name = "Authorization";
    private String prefix = "Bearer ";
  }
}
