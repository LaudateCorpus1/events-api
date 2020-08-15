package br.com.rgrassi.events.security.user;

import br.com.rgrassi.events.model.ApplicationUser;
import br.com.rgrassi.events.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final ApplicationUserRepository repository;

  public UserDetailsServiceImpl(ApplicationUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ApplicationUser applicationUser = repository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(
                    String.format("User '%s' not found", username)
            ));

    return new UserDetailsImpl(applicationUser);
  }
}
