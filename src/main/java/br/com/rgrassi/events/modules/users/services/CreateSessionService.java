package br.com.rgrassi.events.modules.users.services;

import br.com.rgrassi.events.error.IllegalArgumentException;
import br.com.rgrassi.events.modules.users.dtos.CreateSessionResponseDTO;
import br.com.rgrassi.events.modules.users.mongodb.entities.ApplicationUser;
import br.com.rgrassi.events.modules.users.mongodb.repositories.ApplicationUserRepository;
import br.com.rgrassi.events.security.service.TokensService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateSessionService {
  private final TokensService tokensService;
  private final ApplicationUserRepository applicationUserRepository;

  public CreateSessionResponseDTO createSession(AuthenticationManager authenticationManager, String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

      ApplicationUser applicationUser = applicationUserRepository.findByUsername(username).get();

      String token = tokensService.generateToken(applicationUser);
      return CreateSessionResponseDTO.builder().applicationUser(applicationUser).token(token).build();
    } catch (AuthenticationException e) {
      throw new IllegalArgumentException("Incorrect email/password combination");
    }
  }
}
