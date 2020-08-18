package br.com.rgrassi.events.modules.events.mongodb.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event {
  @Id
  @EqualsAndHashCode.Include
  private String id;

  @NotEmpty
  private String name;

  @NotNull
  private LocalDateTime date;

  private String userId;
}
