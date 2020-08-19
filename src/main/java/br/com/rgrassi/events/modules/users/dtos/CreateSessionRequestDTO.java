package br.com.rgrassi.events.modules.users.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateSessionRequestDTO {
  private String username;
  private String password;
}
