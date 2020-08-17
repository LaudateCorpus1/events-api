package br.com.rgrassi.events.error;

public class ResourceNotFoundDetails extends ErrorDetails {
  public ResourceNotFoundDetails(String title, int status, String details, long timestamp, String developerMessage) {
    super(title, status, details, timestamp, developerMessage);
  }
}
