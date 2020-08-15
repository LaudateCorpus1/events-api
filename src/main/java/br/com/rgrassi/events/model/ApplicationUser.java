package br.com.rgrassi.events.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

  private String password;
  private String email;

  public ApplicationUser(@NotNull ApplicationUser applicationUser) {
    this.id = applicationUser.getId();
    this.password = applicationUser.getEmail();
    this.email = applicationUser.getEmail();
  }
}
