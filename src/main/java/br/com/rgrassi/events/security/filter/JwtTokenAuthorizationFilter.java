package br.com.rgrassi.events.security.filter;

import br.com.rgrassi.events.model.ApplicationUser;
import br.com.rgrassi.events.property.JWTConfiguration;
import br.com.rgrassi.events.security.service.TokensService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenAuthorizationFilter extends BasicAuthenticationFilter {
  private final JWTConfiguration jwtConfiguration;
  private final TokensService tokensService;
  private final UserDetailsService userDetailsService;

  public JwtTokenAuthorizationFilter(AuthenticationManager authenticationManager, JWTConfiguration jwtConfiguration, TokensService tokensService, UserDetailsService userDetailsService) {
    super(authenticationManager);
    this.jwtConfiguration = jwtConfiguration;
    this.tokensService = tokensService;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    String header = request.getHeader(jwtConfiguration.getHeader().getName());

    if (header == null || !header.startsWith(jwtConfiguration.getHeader().getPrefix())) {
      chain.doFilter(request, response);
      return;
    }

    String token = header.replace(jwtConfiguration.getHeader().getPrefix(), "").trim();
    ApplicationUser applicationUser = tokensService.parseToken(token);

    UserDetails userDetails = userDetailsService.loadUserByUsername(applicationUser.getUsername());

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
      applicationUser, null, userDetails.getAuthorities()
    );

    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    chain.doFilter(request, response);
  }
}
