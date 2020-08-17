package br.com.rgrassi.events.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails {
  private String title;
  private int status;
  private String details;
  private long timestamp;
  private String developerMessage;
}
