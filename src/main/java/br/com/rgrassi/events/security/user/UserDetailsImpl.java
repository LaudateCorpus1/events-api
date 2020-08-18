package br.com.rgrassi.events.security.user;

import br.com.rgrassi.events.modules.users.mongodb.entities.ApplicationUser;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@ToString
public class UserDetailsImpl extends ApplicationUser implements UserDetails {
  public UserDetailsImpl(@NotNull ApplicationUser applicationUser) {
    super(applicationUser);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

/*  @Override
  public String getUsername() {
    return this.getUsername();
  }*/

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
