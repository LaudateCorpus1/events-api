package br.com.rgrassi.events.modules.users.mongodb.repositories;

import br.com.rgrassi.events.modules.users.mongodb.entities.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String> {
  Optional<ApplicationUser> findByUsername(String username);
}
