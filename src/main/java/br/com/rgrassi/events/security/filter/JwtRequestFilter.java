package br.com.rgrassi.events.security.filter;

import br.com.rgrassi.events.error.IllegalArgumentException;
import br.com.rgrassi.events.modules.users.mongodb.entities.ApplicationUser;
import br.com.rgrassi.events.property.JWTConfiguration;
import br.com.rgrassi.events.security.service.TokensService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
  private final JWTConfiguration jwtConfiguration;
  private final TokensService tokensService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    String header = request.getHeader(jwtConfiguration.getHeader().getName());

    ApplicationUser applicationUser = null;

    if (header != null && header.startsWith(jwtConfiguration.getHeader().getPrefix())) {
      String token = header.replace(jwtConfiguration.getHeader().getPrefix(), "").trim();

      try {
        applicationUser = tokensService.parseToken(token);
      } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) {
        throw new RuntimeException("Invalid JWT token");
      } catch (IllegalArgumentException e) {
        throw new RuntimeException("JWT Token is missing");
      }
    }

    if (applicationUser != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(applicationUser.getUsername());

      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
        userDetails, null, Collections.emptyList());

      usernamePasswordAuthenticationToken
        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
    chain.doFilter(request, response);
  }
}
