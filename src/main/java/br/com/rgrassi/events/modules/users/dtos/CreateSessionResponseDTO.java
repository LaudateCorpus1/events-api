package br.com.rgrassi.events.modules.users.dtos;

import br.com.rgrassi.events.modules.users.mongodb.entities.ApplicationUser;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class CreateSessionResponseDTO {
  private String token;
  private ApplicationUser applicationUser;
}
