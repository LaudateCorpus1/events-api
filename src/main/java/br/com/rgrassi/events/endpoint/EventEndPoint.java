package br.com.rgrassi.events.endpoint;

import br.com.rgrassi.events.error.ResourceNotFoundException;
import br.com.rgrassi.events.model.ApplicationUser;
import br.com.rgrassi.events.model.Event;
import br.com.rgrassi.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("v1/events")
public class EventEndPoint {
  private final EventRepository eventRepository;

  @GetMapping()
  public ResponseEntity<List<Event>> listAll(Authentication authentication) {
    return new ResponseEntity(eventRepository.findAll(), HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<Event> save(@Valid @RequestBody Event event, Authentication authentication) {
    ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
    event.setUserId(applicationUser.getId());
    eventRepository.insert(event);

    return new ResponseEntity(event, HttpStatus.CREATED);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Event> getEventById(@PathVariable("id") String id) {
    verifyIfEventExists(id);

    return new ResponseEntity(eventRepository.findById(id).get(), HttpStatus.OK);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") String id) {
    verifyIfEventExists(id);

    eventRepository.deleteById(id);

    return new ResponseEntity(HttpStatus.OK);
  }

  private void verifyIfEventExists(String id) {
    eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found for ID: " + id));
  }
}
