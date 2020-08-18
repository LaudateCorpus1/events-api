package br.com.rgrassi.events.security.filter;

import br.com.rgrassi.events.modules.users.mongodb.entities.ApplicationUser;
import br.com.rgrassi.events.property.JWTConfiguration;
import br.com.rgrassi.events.security.service.TokensService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final TokensService tokensService;
  private final JWTConfiguration jwtConfiguration;

  @Override
  @SneakyThrows
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    ApplicationUser applicationUser = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);

    if (applicationUser == null) {
      throw new UsernameNotFoundException("Unable to retrieve the username or password");
    }

    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(applicationUser.getUsername(), applicationUser.getPassword());

    try {
      return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    } catch(AuthenticationException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
    ApplicationUser applicationUser = (ApplicationUser) auth.getPrincipal();

    String token = tokensService.generateToken(applicationUser);

    response.getWriter().write(jwtConfiguration.getHeader().getPrefix() + token);
    response.addHeader(jwtConfiguration.getHeader().getName(), jwtConfiguration.getHeader().getPrefix() + token);
  }
}


