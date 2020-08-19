package br.com.rgrassi.events.error;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ErrorDetails {
  private String title;
  private int status;
  private String details;
  private long timestamp;
  private String developerMessage;
}
