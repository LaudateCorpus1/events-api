package br.com.rgrassi.events.modules.users.controllers;

import br.com.rgrassi.events.modules.users.dtos.CreateSessionRequestDTO;
import br.com.rgrassi.events.modules.users.dtos.CreateSessionResponseDTO;
import br.com.rgrassi.events.modules.users.services.CreateSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class SessionsController {
  private final AuthenticationManager authenticationManager;
  private final CreateSessionService createSessionService;

  @PostMapping(path = "/sessions")
  public ResponseEntity<CreateSessionResponseDTO> createSession(@RequestBody CreateSessionRequestDTO dto) {
    CreateSessionResponseDTO responseDTO = createSessionService.createSession(authenticationManager, dto.getUsername(), dto.getPassword());

    return new ResponseEntity(responseDTO, HttpStatus.OK);
  }
}
