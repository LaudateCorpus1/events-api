package br.com.rgrassi.events.modules.events.services;

import br.com.rgrassi.events.error.ResourceNotFoundException;
import br.com.rgrassi.events.modules.events.mongodb.entities.Event;
import br.com.rgrassi.events.modules.events.repositories.IEventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
  private final IEventsRepository eventRepository;

  public List<Event> listAll() {
    return eventRepository.findAll().get();
  }

  public Event save(Event event) {
    eventRepository.save(event);
    return event;
  }

  public Event getById(String id) {
    verifyIfEventExists(id);
    return eventRepository.findById(id).get();
  }

  public void deleteById(String id) {
    verifyIfEventExists(id);
    eventRepository.deleteById(id);
  }

  public void update(Event event) {
    verifyIfEventExists(event.getId());
    eventRepository.update(event);
  }

  private void verifyIfEventExists(String id) {
    eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found for ID: " + id));
  }
}
