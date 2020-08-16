package br.com.rgrassi.events.security.service;

import br.com.rgrassi.events.model.ApplicationUser;
import br.com.rgrassi.events.property.JWTConfiguration;
import com.sun.security.auth.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokensServiceImpl implements TokensService {
  private final JWTConfiguration jwtConfiguration;

  @Override
  public String generateToken(ApplicationUser applicationUser) {
    String compactToken;
    try {
      compactToken = Jwts.builder()
              .claim("userId", applicationUser.getId())
              .claim("sub", applicationUser.getUsername())
              .setExpiration(new Date(System.currentTimeMillis() + jwtConfiguration.getExpiration()))
              .signWith(SignatureAlgorithm.HS256, jwtConfiguration.getSecretKey())
              .compact();
    } catch(RuntimeException e) {
      throw new RuntimeException(e);
    }

    return compactToken;
  }

  @Override
  public ApplicationUser parseToken(String token) {
    Jws<Claims> claims = Jwts.parser()
      .setSigningKey(jwtConfiguration.getSecretKey())
      .parseClaimsJws(token);

    String username = claims.getBody().getSubject();
    String userId = claims.getBody().get("userId", String.class);

    ApplicationUser applicationUser = ApplicationUser
      .builder()
      .id(userId)
      .username(username)
      .build();

    return applicationUser;
  }
}
