package br.com.rgrassi.events.modules.events.controllers;

import br.com.rgrassi.events.modules.users.mongodb.entities.ApplicationUser;
import br.com.rgrassi.events.modules.events.mongodb.entities.Event;
import br.com.rgrassi.events.modules.events.services.EventService;
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
public class EventController {
  private final EventService eventService;

  @GetMapping()
  public ResponseEntity<List<Event>> listAll() {
    return new ResponseEntity(eventService.listAll(), HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<Event> save(@Valid @RequestBody Event event, Authentication authentication) {
    ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
    event.setUserId(applicationUser.getId());

    eventService.save(event);

    return new ResponseEntity(event, HttpStatus.CREATED);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Event> getById(@PathVariable("id") String id) {
    return new ResponseEntity(eventService.getById(id), HttpStatus.OK);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") String id) {
    eventService.deleteById(id);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @PutMapping
  public ResponseEntity<?> update(@RequestBody Event event) {
    eventService.update(event);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
