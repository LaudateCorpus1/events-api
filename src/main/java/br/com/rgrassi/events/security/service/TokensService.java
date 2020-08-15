package br.com.rgrassi.events.security.service;

import br.com.rgrassi.events.model.ApplicationUser;

public interface TokensService {
  String generateToken(ApplicationUser applicationUser);
  ApplicationUser parseToken(String token);
}
