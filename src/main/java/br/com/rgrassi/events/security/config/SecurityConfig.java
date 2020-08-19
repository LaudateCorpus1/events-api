package br.com.rgrassi.events.security.config;

import br.com.rgrassi.events.property.JWTConfiguration;
import br.com.rgrassi.events.security.filter.JwtTokenAuthorizationFilter;
import br.com.rgrassi.events.security.filter.JwtUsernameAndPasswordAuthenticationFilter;
import br.com.rgrassi.events.security.service.TokensService;
import br.com.rgrassi.events.security.user.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final UserDetailsServiceImpl userDetailsService;
  private final JWTConfiguration jwtConfiguration;
  private final TokensService tokensService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
      .and()
      .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
        .exceptionHandling().authenticationEntryPoint((request, response, e) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.toString()))
      .and()
        .authorizeRequests()
          .antMatchers(HttpMethod.POST, jwtConfiguration.getLoginUrl()).permitAll()
          .antMatchers(HttpMethod.POST, jwtConfiguration.getSingUpUrl()).permitAll()
          .anyRequest().authenticated()
      .and()
        .httpBasic().disable()
        .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), tokensService, jwtConfiguration))
        .addFilter(new JwtTokenAuthorizationFilter(authenticationManager(), jwtConfiguration, tokensService, userDetailsService));
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
