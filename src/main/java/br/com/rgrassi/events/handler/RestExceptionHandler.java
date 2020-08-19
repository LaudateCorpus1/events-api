package br.com.rgrassi.events.handler;

import br.com.rgrassi.events.error.IllegalArgumentDetails;
import br.com.rgrassi.events.error.IllegalArgumentException;
import br.com.rgrassi.events.error.ResourceNotFoundDetails;
import br.com.rgrassi.events.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException) {
    return new ResponseEntity(ResourceNotFoundDetails.builder()
      .title("Resource not found")
      .status(HttpStatus.NOT_FOUND.value())
      .details(rnfException.getMessage())
      .timestamp(new Date().getTime())
      .developerMessage(rnfException.getClass().getName())
      .build(),
    HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException iaException) {
    return new ResponseEntity(IllegalArgumentDetails.builder()
      .title("Bad request")
      .status(HttpStatus.BAD_REQUEST.value())
      .details(iaException.getMessage())
      .timestamp(new Date().getTime())
      .developerMessage(iaException.getClass().getName())
      .build(),
    HttpStatus.BAD_REQUEST);
  }
}
