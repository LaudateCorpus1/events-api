package br.com.rgrassi.events.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Document(collection = "event")
public class Event {
  @Id
  private String id;
  private String name;
  private LocalDateTime date;
  private ApplicationUser user;
}
