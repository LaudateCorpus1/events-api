package br.com.rgrassi.events.modules.users.mongodb.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ApplicationUser {
  @Id
  @EqualsAndHashCode.Include
  private String id;

  @NotEmpty
  private String password;

  @NotEmpty @Email
  private String username;

  public ApplicationUser(@NotNull ApplicationUser applicationUser) {
    this.id = applicationUser.getId();
    this.password = applicationUser.getPassword();
    this.username = applicationUser.getUsername();
  }
}
