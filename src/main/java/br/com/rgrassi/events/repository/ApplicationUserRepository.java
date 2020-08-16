package br.com.rgrassi.events.repository;

import br.com.rgrassi.events.model.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String> {
  Optional<ApplicationUser> findByUsername(String email);
}
