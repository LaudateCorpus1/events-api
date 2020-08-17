package br.com.rgrassi.events.handler;

import br.com.rgrassi.events.error.ResourceNotFoundDetails;
import br.com.rgrassi.events.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException) {
    ResourceNotFoundDetails rnfDetails = new ResourceNotFoundDetails(
      "Resource not found",
      HttpStatus.NOT_FOUND.value(),
      rnfException.getMessage(),
      new Date().getTime(),
      rnfException.getClass().getName());

    return new ResponseEntity(rnfDetails, HttpStatus.NOT_FOUND);
  }
}
