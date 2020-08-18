package br.com.rgrassi.events.security.service;

import br.com.rgrassi.events.modules.users.mongodb.entities.ApplicationUser;

public interface TokensService {
  String generateToken(ApplicationUser applicationUser);
  ApplicationUser parseToken(String token);
}
